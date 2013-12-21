package javadoc;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;

/**
 * JavadocDemo - a simple applet to show JavaDoc comments.
 * <p>Note: this is just a commented version of HelloApplet.
 * @author Ian F. Darwin, <a href="mailto:http://www.darwinsys.com/">http://www.darwinsys.com/</A>
 * @see java.applet.Applet
 * @see javax.swing.JApplet
 */
// BEGIN main
public class JavadocDemo extends Applet {

	/** init() is an Applet method called by the browser to initialize.
	 * Init normally sets up the GUI, and this version is no exception.
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
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, w/2, h);
		g.setColor(Color.GREEN);
		g.fillRect(w/2, 0, w, h);
		g.setColor(Color.BLACK);
		g.drawString("Welcome to Java", 50, 50);
	}

	/** Show makes a component visible; this method became deprecated
	 * in the Great Renaming of JDK1.1.
	 * @since 1.0
	 * @deprecated Use setvisible(true) instead.
	 */
	public void show() {
		setVisible(true);
	}

	/** An Applet must have a public no-argument constructor.
	 * @throws java.lang.IllegalArgumentException on Sundays.
	 */
	public JavadocDemo() {
		if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 0) {
			throw new IllegalArgumentException("Never On A Sunday");
		}
	}
}
// END main
