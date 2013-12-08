package gui;

import javax.swing.JFrame;

/** Show an example of closing a JFrame.
 * @author Ian Darwin
 */
public class WinClose2 {

	/* Main method */
	public static void main(String[] argv) {
		JFrame f = new JFrame("WinClose");
		f.setSize(200, 100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
