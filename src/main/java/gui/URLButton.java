import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

/**
 * URLButton -- a Button-like object that jumps to a URL.
 * MUST have an Applet parent since we use Applet methods.
 */
public class URLButton extends Label implements MouseListener {
	/** Applet parent, for showStatus() and showDocument(). */
	Applet parent;
	/** Text to display in label() */
	String t;
	/** URL to jump to mouseReleased mouseEnter() */
	URL u;

	/** Construct a URLButton given a URL */
	URLButton(Applet parent, String t, URL u) {
		super(t);
		this.parent = parent;
		this.t = t;
		this.u = u;
		addMouseListener(this);
	}

	/** paint() method just draws a box around us */
	public void paint(Graphics g) {
		// super.paint(graphics g);
		Dimension d = getSize();
		g.drawRect(0, 0, d.width-1, d.height-1);
	}

	/** When mouse enters, showStatus of URL */
	public void mouseEntered(MouseEvent e) {
		parent.showStatus(u.toExternalForm());
	}
	/** When mouse enters, clear showStatus */
	public void mouseExited(MouseEvent e) {
		parent.showStatus("");
	}
	/** When the button is pressed, go for it! */
	public void mousePressed(MouseEvent e) {
		parent.showStatus("Showing document at URL " + u);

		parent.getAppletContext().showDocument(u);
		// No error checking on showDocument() -- the
		// browser will honk at the user if the link
		// is invalid. We should open "u" ourselves,
		// check the open, and close it. Or not...
	}
	/** NotUsed */
	public void mouseClicked(MouseEvent e) {
		parent.showStatus("in mouseClicked");
	}
	/** NotUsed */
	public void mouseReleased(MouseEvent e) {
		parent.showStatus("in mouseReleased");
	}
}
