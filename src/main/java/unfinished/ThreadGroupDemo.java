package unfinished;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Show how to catch exceptions from action handlers' threads.
 * DOES NOT WORK -- DO NOT USE
 */
public class ThreadGroupDemo {

	public static void main(String[] args) {

		// Create a Threadgroup subclass, and use it to catch any
		// exceptions that are uncaught in the various
		// threads' run methods... And exit cleanly.
		class CatchGroup extends ThreadGroup {
			CatchGroup() {
				super("CatchGroup");
			}
			public void uncaughtException(Thread t, Throwable e) {
				System.err.println("********************************");
				System.err.println("Caught " + e);
				e.printStackTrace(System.err);
				System.err.println("********************************");
				// System.exit(0);
			}
		};

		// Construct a Runnable to create and start the GUI.
		// This creates some new threads, like the AWT event queue thread.
		// The button's action handler deliberately throws an Exception,
		// of course, just to see if we can catch it.
		Runnable guiThrower = new Runnable() {
			public void run() {
				JFrame f = new JFrame("ThreadGroupDemo");
				JButton b = new JButton("Throw");
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						throw new IllegalArgumentException(
							"Just a demo exception");
					}
				});
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setContentPane(b);
				f.pack();
				f.setVisible(true);
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException ex) {
					/*canthappen*/
				}
				throw new IllegalArgumentException(
					"Demo at end of timed sleep");
			}
		};

		// Start a new Thread in a "CatchGroup" ThreadGroup to run "catcher".
		// This might make all the GUI threads be children of the "all"
		// group, whose catchException() was overridden.
		new Thread(new CatchGroup(), guiThrower, "GUICatcher").start();
	}
}
