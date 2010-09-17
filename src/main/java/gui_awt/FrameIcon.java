package gui_awt;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;

public class FrameIcon {

	/** Demo main program, showing two ways to use it.
	 * Create a small MemImage and set it as this Frame's iconImage. 
	 * Also display a larger version of the same image in the Frame.
	 */
	public static void main(String[] av) {
		Frame f = new Frame("FrameIcon");
		Image im = 
			Toolkit.getDefaultToolkit().getImage("FrameIcon.gif");
		f.setIconImage(im);
		f.setSize(100, 100);
		f.setLocation(200, 200);
		f.setVisible(true);
	}
}
