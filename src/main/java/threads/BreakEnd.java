import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

/** BreakEnd - 
 * Tool to display current time and time that class starts/resumes.
 *
 * This started as a quick hack when I saw the Windows
 * guys with a tool like this, but it has grown somewhat since then.
 *
 * TODO add another Thread to flash the screen if the resume time is passed.
 *
 * @author	Ian Darwin
 * @version	0.7
 */
public class BreakEnd extends Frame implements Runnable {
	Label nowLabel;
	Label endsLabel;
	Thread t;

	/** Main method to start me up. */
	public static void main(String av[]) {
		for (int i=0; i<av.length; i++) {
			// parse options...
		}

		if (av.length != 1) {
			System.err.println("Usage: javaBreak time");
			return;
		}
		BreakEnd b = new BreakEnd(av[0]);
		b.pack();

		// After packing the Frame, centre it on the screen.
		Dimension us = b.getSize(), 
			them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height- us.height)/ 2;
		b.setLocation(newX, newY);

		b.setVisible(true);
	}

	private String toHHMM_String(Calendar d) {
		String result = null;
		int h = d.get(Calendar.HOUR_OF_DAY);
		int m = d.get(Calendar.MINUTE);
		// To format a non-localized two-digit number
		NumberFormat form = new DecimalFormat("00");
		try {
			// XX Why do we have to add one to hour?
			result = form.format(h+1) + ":" + form.format(m);
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
		String mesg = null;

		Container cp;
		// cp = getContentPane();
		cp = this;
		cp.setLayout(new BorderLayout());

		cp.add(BorderLayout.NORTH,
			nowLabel =  new Label("Time now is: 00:00:00", Label.CENTER));
	
		if (s.startsWith("+")) {	// "This will be HARDer..."
			Calendar d = new GregorianCalendar();
			int newMinutes = d.get(Calendar.MINUTE)+
				Integer.parseInt(s.substring(1))+1;
			d.set(Calendar.MINUTE, newMinutes);
			mesg = "Class resumes at " + toHHMM_String(d);
		} else	mesg = "Class resumes at " + s + " ";
		cp.add(BorderLayout.CENTER,
			endsLabel = new Label(mesg, Label.CENTER));
		Font f = new Font("Helvetica", Font.PLAIN, 42);
		nowLabel.setFont(f);
		endsLabel.setFont(f);
		Button b;
		cp.add(BorderLayout.SOUTH, b = new Button("Done"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		(t = new Thread(this)).start();

        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			setVisible(false);
			dispose();
			System.exit(0);
			}
		});
	}
}
