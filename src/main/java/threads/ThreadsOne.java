import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/**
 * Solution to the old Threaded Label exercise: each instance of this class
 * runs its own thread, with a counter that counts down to zero.
 * 
 * @author	Ian Darwin
 *
 * @version	0.5, July 1997, for JDK1.1
 */
public class ThreadsOne extends Applet implements Runnable {
	Label lab;	
	Button but;
	Thread t;
	int count;

	/** run() - do the work of the thread. Might get confused if
	 * the user pushes the button a second time before we finish.
	 */
	public void run() {
		int c = count;
		while (c-- > 0) {
			lab.setText(Integer.toString(c));
			try {
				Thread.sleep(100);	// 100 msec
			} catch (Exception e) {
				return;
			}
		}
		System.out.println("All done");
	}

	/** Set the number of times the counter is to decrement */
	public void setCount(int i) {
		count = i;
	}

	/** init() [from Applet] - set up the GUI */
	public void init() {

		// Create the start button.
		add(but = new Button("Start"));
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			t = new Thread(ThreadsOne.this);
			t.start();
			}
		});

		// Make label large enough to hold three-digit number
		add(lab = new Label("000"));
	
		// Provide a count from the PARAM, default to 100.
		String snum;
		if ((snum = getParameter("count")) != null)
			setCount(Integer.parseInt(snum));
		else
			setCount(100);
	}
}
