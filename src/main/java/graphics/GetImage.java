
/*
<APPLET CODE=GetImage.class WIDTH=100 HEIGHT=100>
</APPLET>
<--
*/

import java.applet.*;
import java.net.*;
import java.awt.*;

/** This program, which can be an Applet or an Application,
 * shows a form of Toolkit.getImage() which works the same
 * in either Applet or Application!
 */
public class GetImage extends Applet {

	Image image;

	public void init() {
		loadImage();
	}

	public void loadImage() {
		URL url = getClass().getResource("visa.gif");
		// System.out.println(url);
		image = Toolkit.getDefaultToolkit().getImage(url);
	}

	public void paint(Graphics g) {
		g.drawImage(image, 20, 20, this);
	}

	public static void main(String args[]) {
		Frame f = new Frame("GetImage");
		GetImage myApplet = new GetImage();
		f.add(myApplet);
		myApplet.init();
		f.setSize(100, 100);
		f.setVisible(true);
	}
}
