/*
 * For Applet, invoke as:
 * <applet code="GetImage" width="100" height="100">
 * </applet>
 * For Application, just run it (has own main).
 */

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JFrame;

/** This program, which can be an Applet or an Application,
 * shows a form of Toolkit.getImage() which works the same
 * in either Applet or Application!
 */
public class GetImage extends JApplet {

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
		JFrame f = new JFrame("GetImage");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GetImage myApplet = new GetImage();
		f.getContentPane().add(myApplet);
		myApplet.init();
		f.setSize(100, 100);
		f.setVisible(true);
		myApplet.start();
	}
}
