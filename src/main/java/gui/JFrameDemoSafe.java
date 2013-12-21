package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

/** Create a JFrame class in a thread-safe way.
 * <br/>
 * See http://java.sun.com/developer/JDCTechTips/2003/tt1208.html.
 */
// BEGIN main
public class JFrameDemoSafe {
	// We need a main program to instantiate and show.
	public static void main(String[] args) {

		// Create the GUI (variable is final because used by inner class).
		final JFrame demo = new JFrameDemo();

		// Create a Runnable to set the main visible, and get Swing to invoke.
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				demo.setVisible(true);
			}
		});
	}
}
// END main
