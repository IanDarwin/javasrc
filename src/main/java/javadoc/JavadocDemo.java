import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 * DocDemo - a simple applet to show JavaDoc comments.
 * <P>Note: this is just a commented version of HelloApplet.
 * @author Ian F. Darwin, <A HREF="mailto:ian@darwinsys.com">ian@darwinsys.com</A>
 * @version $Id$
 * @see	java.applet.Applet
 * @see javax.swing.JApplet
 */
public class DocDemo extends Applet {

	/** init() is an Applet method called by the browser to initialize.
	 * Init normally sets up the GUI, and this version is no exception.
	 * @return	None.
	 */
	public void init() {
		// We create and add a pushbutton here, 
		// but it doesn't do anything yet.
		Button b;
		b = new Button("Hello");
		add(b);						// connect Button into Applet
	}

	/** paint() is an AWT Component method, called when the 
	 *  component needs to be painted. This one just draws colored
	 * boxes in the Applet's window.
	 *
	 * @param g A java.awt.Graphics that we use for all our
	 * drawing methods.
	 */
	public void paint(Graphics g) {
		int w = getSize().width, h=getSize().height;
		g.setColor(Color.yellow);
		g.fillRect(0, 0, w/2, h);
		g.setColor(Color.green);
		g.fillRect(w/2, 0, w, h);
		g.setColor(Color.black);
		g.drawString("Welcome to Java", 50, 50);
	}
}
