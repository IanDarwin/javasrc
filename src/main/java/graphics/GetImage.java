/*
 * For Applet, invoke as:
 * <applet code="GetImage" width="100" height="100">
 * </applet>
 * For Application, just run it (has own main).
 */

import java.awt.*;
import java.net.*;		// for URL class

import com.darwinsys.swingui.WindowCloser;	// EXPECT DEPRECATION WARNING
// This program could be updated to JApplet and JFrame if anybody has time.

/** This program, which can be an Applet or an Application,
 * shows a form of Toolkit.getImage() which works the same
 * in either Applet or Application!
 */
public class GetImage extends java.applet.Applet {

	Image image;

	public void init() {
		loadImage();
	}

	public void loadImage() {
		// Applet-only version:
		// Image = getImage(getCodeBase(), "Duke.gif");
		
		// Portable version: getClass().getResource() works in either
		// applet or application, 1.1 or 1.3, returns URL for file name.
		URL url = getClass().getResource("Duke.gif");
		image = getToolkit().getImage(url);
		// Shorter portable version: same but avoids temporary variables
		// image = getToolkit().getImage(getClass().getResource("Duke.gif"));
	}

	public void paint(Graphics g) {
		g.drawImage(image, 20, 20, this);
	}

	public static void main(String[] args) {
		Frame f = new Frame("GetImage");
        f.addWindowListener(new WindowCloser(f));
		GetImage myApplet = new GetImage();
		f.add(myApplet);
		myApplet.init();
		f.setSize(100, 100);
		f.setVisible(true);
		myApplet.start();
	}
}
