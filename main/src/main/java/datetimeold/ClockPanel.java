package datetimeold;

import java.awt.Graphics;
import java.time.LocalDateTime;

import javax.swing.JPanel;

/** An JPanel to display the current time */
public class ClockPanel extends JPanel {

	private static final long serialVersionUID = 1321097174379241717L;
	/** A Thread to run the timer */
	protected Thread timerThread;

	public ClockPanel() {
		timerThread = new Thread(() -> {
			while (timerThread != null) {
				repaint();	// request a redraw
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e){ /* do nothing*/ }
			}
		});
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
		g.drawString(LocalDateTime.now().toString(), 2, 10);
	}
}
