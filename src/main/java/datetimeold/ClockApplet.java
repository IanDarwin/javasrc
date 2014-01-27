package datetimeold;

import java.applet.Applet;
import java.awt.Graphics;
import java.text.DateFormat;
import java.util.Date;

/** An Applet to display the current time */
public class ClockApplet extends Applet implements Runnable {

	private static final long serialVersionUID = 1321097174379241717L;
	/** A Thread to run the timer */
	protected Thread timerThread;
	/** The date object */
	Date date = new Date();
	/** The date format */
	protected final DateFormat format = DateFormat.getTimeInstance();

	/* Applet Lifestyle Methods */
	public void start() {
		timerThread = new Thread(this, "Clock");
		timerThread.start();
	}

	public void stop() {
		if (timerThread == null)
			return;
		timerThread = null;
	}
 
	/** Show the time, and wait a while. */
	public void run() {
		while (timerThread != null) {
			repaint();	// request a redraw
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e){ /* do nothing*/ }
		}
	}

	/** Display the time. */
	public void paint(Graphics g) {
		date.setTime(System.currentTimeMillis());
		g.drawString(format.format(date), 2, 10);
	}
}
