//+
/*
 * For Applet, invoke as:
<APPLET CODE="GetImage" WIDTH="100" HEIGHT="100">
</APPLET>
 * For Application, just run it (has own main).
 */

import java.awt.*;
import java.net.*;		// for URL class

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
		
		// Portable version:
		URL url = getClass().getResource("Duke.gif");
		image = getToolkit().getImage(url);
		// Shorter portable version:
		image = getToolkit().getImage(getClass().getResource("Duke.gif"));
	}

	public void paint(Graphics g) {
		g.drawImage(image, 20, 20, this);
	}

	public static void main(String[] args) {
		Frame f = new Frame("GetImage");
		//-
        f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// If we do setVisible and dispose, then the Close completes
				Outer.this.setVisible(false);
				Outer.this.dispose();
			}
		});
		//+
		GetImage myApplet = new GetImage();
		f.add(myApplet);
		myApplet.init();
		f.setSize(100, 100);
		f.setVisible(true);
		myApplet.start();
	}
}
