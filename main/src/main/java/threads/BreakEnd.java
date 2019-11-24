package threads;

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id$
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *        This product includes software developed by Ian F. Darwin.
 * 4. Neither the name of the author nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.darwinsys.swingui.UtilGUI;

/** BreakEnd - 
 * Tool to display current time and time that class starts/resumes.
 *
 * This started as a quick hack when I saw the Windows
 * guys with a tool like this, but it has grown somewhat since then.
 *
 * TODO 
 * <ul>
 *	<li>add another Thread to flash the screen if the resume time is passed.
 * </ul>
 *
 * @author	Ian Darwin
 */
public class BreakEnd extends JFrame implements Runnable {

	private static final long serialVersionUID = 3543497759040697383L;
	/** Label for the current time. */
	protected JLabel nowLabel;
	/** Label for when break ends. */
	protected JLabel endsLabel;
	/** JSlider for font sizes */
	protected JSlider fontSize;
	/** A thread to run the clock ticker */
	protected Thread ticker;
	/** The font for the large text */
	protected Font f;
	/** The minimum allowable font size */
	protected static final int FONT_SIZE_MIN = 10;
	/** The maximum sensible font size given current monitors. */
	protected static final int FONT_SIZE_MAX = 150;
	/** Default point size */
	protected static final int FONT_SIZE_DEFAULT = 50;

	/** Main method to start me up. */
	public static void main(String[] av) {
		for (int i=0; i<av.length; i++) {
			// parse options...
		}

		if (av.length != 1) {
			System.err.println("Usage: javaBreak time");
			return;
		}
		BreakEnd b = new BreakEnd(av[0]);
		UtilGUI.centre(b);

		b.setVisible(true);
	}

	/** NumberFormat to format a non-localized two-digit number */
	protected NumberFormat form = new DecimalFormat("00");

	/** Get the Calendar into HHMM format */
	private String toHHMM_String(Calendar d) {
		String result = null;
		int h = d.get(Calendar.HOUR_OF_DAY);
		int m = d.get(Calendar.MINUTE);
		int s = d.get(Calendar.SECOND);
		try {
			result = form.format(h) + ":" +
				form.format(m) + ":" +
				form.format(s);
			// Why not just use a DateFormat for that?
		} catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(this,
				"Formatting Error!" + iae,	// message
				"Formatting Error!",					// titlebar
				JOptionPane.ERROR_MESSAGE);	// icon
		}
		return result;
	}

	public void run() {
		Calendar d /* = new GregorianCalendar() */; // For 1.4
		while (ticker.isAlive()) {
			//d.setTimeInMillis(System.currentTimeMillis()); // For 1.4
			d = new GregorianCalendar();
			nowLabel.setText("Time is now " + toHHMM_String(d));
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				return;
			}
		}
	}

	/** Constructor: set a font, initialize UI components. */
	public BreakEnd(String s) {
		super("Java Breaker: " + s);
		setBackground(Color.white);
		setForeground(Color.red);

		Container cp;
		cp = getContentPane();
		// cp = this;
		cp.setLayout(new BorderLayout());

		fontSize = new JSlider(SwingConstants.HORIZONTAL,
			FONT_SIZE_MIN, FONT_SIZE_MAX, FONT_SIZE_DEFAULT);
		fontSize.setMajorTickSpacing(10);
		fontSize.setMinorTickSpacing(5);
		fontSize.setSnapToTicks(true);
		fontSize.setPaintTicks(true);
		fontSize.setPaintLabels(true);
		fontSize.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));


		fontSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (!source.getValueIsAdjusting()) {
					int points = (int)source.getValue();
					setFontSize(points);
				}
			}
		});
		//add the slider to the content pane
		cp.add(BorderLayout.NORTH, fontSize);

		// Make a panel for CENTER to hold two labels above each other
		JPanel timesPanel = new JPanel();
		timesPanel.setLayout(new GridLayout(2,1));

		cp.add(BorderLayout.CENTER, timesPanel);
		timesPanel.add(
			nowLabel =  new JLabel("Time now is: 00:00:00", SwingConstants.CENTER));
	
		String mesg = null;
		if (s.startsWith("+")) {	// "This will be HARDer..."
			Calendar d = new GregorianCalendar();
			int newMinutes = d.get(Calendar.MINUTE)+
				Integer.parseInt(s.substring(1))+1;
			d.set(Calendar.MINUTE, newMinutes);
			mesg = "We start at " + toHHMM_String(d);
		} else {
			mesg = "We start at " + s + " ";
		}
		timesPanel.add(BorderLayout.CENTER,
			endsLabel = new JLabel(mesg, SwingConstants.CENTER));
		setFontSize(FONT_SIZE_DEFAULT);
		JButton b;
		cp.add(BorderLayout.SOUTH, b = new JButton("Done"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Pack should be done here so the ComponentAdapter's
		// componentResized doesn't fire and shrink the size
		pack();

		// Start the timer thread now. Tick, tick, tick.
		(ticker = new Thread(this)).start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension d = getSize();
				if (d.height < 100)
					setFontSize(15);
				else
					setFontSize(d.height/6);
			}
		});
	}

	/** Set the font to the given size */
    protected void setFontSize(int sz) {
		if (sz > FONT_SIZE_MAX)
			sz = FONT_SIZE_MAX;
		//System.out.println("Setting font size to " + sz);
		Font font = new Font("Helvetica", Font.PLAIN, sz);
		nowLabel.setFont(font);
		endsLabel.setFont(font);
		fontSize.setValue(sz);
	}
}
