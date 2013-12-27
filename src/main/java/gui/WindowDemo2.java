package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/* Show an example of closing a JFrame.
 * @author Ian Darwin
 */
// BEGIN main
public class WindowDemo2 extends JFrame {

	public static void main(String[] argv) {
		JFrame f = new WindowDemo2();
		f.setVisible(true);
	}
	public WindowDemo2() {
		setSize(200, 100);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowDemoAdapter());
	}

	/** Named Inner class that closes a Window. */
	class WindowDemoAdapter extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			// whimsy - close randomly, ~ 1 times in 3
			if (Math.random() > 0.666) {
				System.out.println("Goodbye!");
				WindowDemo2.this.setVisible(false);	// window will close
				WindowDemo2.this.dispose();		// and be freed up.
				System.exit(0);
			} 
			System.out.println("You asked me to close, but not to I chose.");
		}
	}
}
// END main
