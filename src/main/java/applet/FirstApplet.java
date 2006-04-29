package applet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FirstApplet is a simple applet that changes color when you click on a
 * Draw button.
 */
public class FirstApplet extends Applet implements ActionListener {
	private static final long serialVersionUID = 5013968934819168436L;
	boolean requested;

	/** init() is an Applet method called by the browser to initialize.
	 * In this program, we use it to initialize the "requested" flag
	 * to false, create a Draw button and add it to the Applet, and
	 * hang an ActionListener (which is also this Applet itself) onto
	 * the Button.
	 */
	public void init() {
		requested = false;
		Button b;
		b = new Button("Draw");
		add(b);						// connect Button into Applet
		b.addActionListener(this);	// connect Actions back to Applet
	}

	/** actionPerformed is called when a "high level" action happens
	 *  (like the user pushing a Button!) in one of the components for
	 *  which we are registered as an actionListener.  In this applet, 
	 *  we just toggle the state of the "requested" flag, to draw or
	 *  not to draw.
	 */
	public void actionPerformed(ActionEvent e) {
		// Invert or "toggle" the state of the draw request.
		requested = !requested;
		repaint();			// Then ask AWT to repaint us.
	}

	/** paint() is an AWT Component method, called when the 
	 *  component needs to be painted. We look at the draw flag and,
	 * if we've turned it on in actionPerformed, we draw some colored
	 * boxes in the Applet's window.
	 */
	public void paint(Graphics g) {
		/* If the drawn button has been pressed, draw something */
		if (requested) {
			int w = getSize().width, h=getSize().height;
			g.setColor(Color.yellow);
			g.fillRect(0, 0, w/2, h);
			g.setColor(Color.green);
			g.fillRect(w/2, 0, w, h);
			g.setColor(Color.black);
			g.drawString("Welcome to Java", 50, 50);
		}
	}
}
