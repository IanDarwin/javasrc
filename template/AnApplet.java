package template;

import java.applet.Applet;
import java.awt.Graphics;

/* Example of an Applet.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class AnApplet extends Applet {
	public void init() {
		// initialize your applet GUI here
	}

	public void paint(Graphics g) {
		g.drawString("Welcome to Java", 50, 50);
	}
}
