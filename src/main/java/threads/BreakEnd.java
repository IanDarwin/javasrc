/*
 * Copyright (c) Ian F. Darwin, ian@darwinsys.com, 1996-2002.
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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import com.darwinsys.util.*;

/** BreakEnd - 
 * Tool to display current time and time that class starts/resumes.
 *
 * This started as a quick hack when I saw the Windows
 * guys with a tool like this, but it has grown somewhat since then.
 *
 * TODO 
 * <ul>
 *	<li>add another Thread to flash the screen if the resume time is passed.
 *	<li>add a Slider to set the point size, and shrinkwrap to that.
 * </ul>
 *
 * @author	Ian Darwin
 * @version	$Id$
 */
public class BreakEnd extends JFrame implements Runnable {
	protected JLabel nowLabel;
	protected JLabel endsLabel;
	/** A thread to run the clock ticker */
	protected Thread t;
	/** The font for the large text */
	protected Font f;
	/** The maximum sensible font size given current monitors. */
	public static final int MAXFONTSIZE = 200;

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
		b.pack();
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
		try {
			result = form.format(h) + ":" + form.format(m);
		} catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(this,
				"Formatting Error!" + iae,	// message
				"Formatting Error!",					// titlebar
				JOptionPane.ERROR_MESSAGE);	// icon
		}
		return result;
	}

	public void run() {
		while (t.isAlive()) {
			Calendar d = new GregorianCalendar();
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

		cp.add(BorderLayout.NORTH,
			nowLabel =  new JLabel("Time now is: 00:00:00", JLabel.CENTER));
	
		String mesg = null;
		if (s.startsWith("+")) {	// "This will be HARDer..."
			Calendar d = new GregorianCalendar();
			int newMinutes = d.get(Calendar.MINUTE)+
				Integer.parseInt(s.substring(1))+1;
			d.set(Calendar.MINUTE, newMinutes);
			mesg = "Class resumes at " + toHHMM_String(d);
		} else {
			mesg = "Class resumes at " + s + " ";
		}
		cp.add(BorderLayout.CENTER,
			endsLabel = new JLabel(mesg, JLabel.CENTER));
		setFontSize(40);
		JButton b;
		cp.add(BorderLayout.SOUTH, b = new JButton("Done"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		(t = new Thread(this)).start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension d = getSize();
				if (d.height < 100)
					setFontSize(72);
				else
					setFontSize(d.height/6);
			}
		});
	}

	/** Set the font to the given size */
    protected void setFontSize(int sz) {
		if (sz > MAXFONTSIZE)
			sz = MAXFONTSIZE;
		System.out.println("Setting font size to " + sz);
		Font f = new Font("Helvetica", Font.PLAIN, sz);
		nowLabel.setFont(f);
		endsLabel.setFont(f);
	}
}
