package gui;

import java.awt.*;
import java.awt.event.*;

/* Show an example of closing a Window.
 * @author Ian Darwin
 * @version $Id$
 */
public class WindowDemo extends Frame {

	public static void main(String[] argv) {
		Frame f = new WindowDemo();
		f.setVisible(true);
	}
	public WindowDemo() {
		setSize(200, 100);
		addWindowListener(new WindowDemoAdapter());
	}

	/** Named Inner class that closes a Window. */
	class WindowDemoAdapter extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			// whimsy - close randomly, ~ 1 times in 3
			if (Math.random() > 0.666) {
				System.out.println("Goodbye!");
				WindowDemo.this.setVisible(false);	// window will close
				WindowDemo.this.dispose();		// and be freed up.
				System.exit(0);
			} 
			System.out.println("You asked me to close, but not to I chose.");
		}
	}
}
