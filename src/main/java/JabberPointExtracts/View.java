import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** EXTRACTS FROM JabberPoint SlideShow View */
public class View extends Component implements Observer {
	/** The collection of text-points, figures,  on the current page */
	Slide slide;
	/** Our preferred Width */
	int prefWidth;
	/** Our preferred Height */
	int prefHeight;

	/** Construct a View, as big as possible */
	View() {
		setBackground(Color.white);
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		prefWidth = them.width;
		prefHeight = them.height;
	}

	public Dimension getPreferredSize() {
		return new Dimension(prefWidth, prefHeight);
	}

	/** Called from the Model when the current page changes;
	 * save the current page, and call repaint().
	 */
	public void update(Observable model, Object data) {
		slide = (Slide)data;
		repaint();
	}

	/** The Paint routine, draw the text */
	public void paint(Graphics g) {
		int y = 75;		// top margin
		int indent;
		Vector v = slide.getMs();
		/* (an M is a bulletted point of text, a figure, etc.) */
		for (int i=0; i<v.size(); i++) {
			M m = (M)v.elementAt(i);	// get current line
			// not shown: stuff for finding Style (font, color etc.).
			Style s = ...
			indent = s.indent;
			switch(m.type) {
				case M.TEXT:
					g.setFont(s.font);
					g.setColor(s.color);
					g.drawString(m.label, indent, y);
					y += s.leading;
					break;
				// TODO: use subclasses instead of type code
				// and support images and other fancy things
				default:
					throw new IllegalArgumentException(
					"Unknown type (" + m.type + ") found in View.paint()");
			}
		}
	}
}
