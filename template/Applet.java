import java.applet.*;
import java.awt.*;

/* Example of an Applet.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class Applet extends java.applet.Applet {
	public void init() {
		// initialize your applet GUI here
	}

	public void paint(Graphics g) {
		g.drawString("Welcome to Java", 50, 50);
	}
}
