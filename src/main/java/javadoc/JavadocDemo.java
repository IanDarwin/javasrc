package javadoc;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JPanel;

/**
 * JavadocDemo - a simple example to show JavaDoc comments.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class JavadocDemo extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Construct the GUI
	 * @throws java.lang.IllegalArgumentException if constructed on a Sunday.
	 */
	public JavadocDemo() {
		// We create and add a pushbutton here, 
		// but it doesn't do anything yet.
		Button b = new Button("Hello");
		add(b);						// connect Button into component
		// Totally capricious example of what you should not do
		if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			throw new IllegalArgumentException("Never On A Sunday");
		}
	}

	/** paint() is an AWT Component method, called when the 
	 *  component needs to be painted. This one just draws colored
	 * boxes in the window.
	 *
	 * @param g A java.awt.Graphics that we use for all our
	 * drawing methods.
	 */
	public void paint(Graphics g) {
		int w = getSize().width, h = getSize().height;
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, w/2, h);
		g.setColor(Color.GREEN);
		g.fillRect(w/2, 0, w, h);
		g.setColor(Color.BLACK);
		g.drawString("Welcome to Java", 50, 50);
	}
}
// END main
