import java.applet.*;
import java.awt.*;
import java.util.Date;
import java.text.DateFormat;

/** An Applet to display the current time */
public class ClockApplet extends Applet implements Runnable {
	/** A Thread to run the timer */
	protected Thread timerThread;
	/** The date object */
	Date date = new Date();
	/** The date format */
	protected DateFormat format = DateFormat.getTimeInstance();

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
				timerThread.sleep(1000);
			} catch (InterruptedException e){ /* do nothing*/ }
		}
	}

	/** Display the time. */
	public void paint(Graphics g) {
		date.setTime(System.currentTimeMillis());
		g.drawString(format.format(date), 2, 10);
	}
}
