package netweb;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/** Synthetic button, not using Native toolkit. This is slightly fancier
 * than the XButton* classes, but still does not do everything you
 * might want in a button. It does both support actionListeners AND
 * have a TARGET URL that it jumps to (after notifying the actionListeners).
 *
 * @author	Copyright 1995, 1997 Ian F. Darwin,
 * <A HREF="mailto:http://www.darwinsys.com/">http://www.darwinsys.com/</A>,
 * <A HREF="http:www.darwinsys.com/">http://www.darwinsys.com</A>.
 */
public class LinkButton extends Applet implements MouseListener {

	private static final long serialVersionUID = -3074898744148442497L;
	/** The label that is to appear in the button */
	protected String label = null;
	/** The name of the image */
	protected String imName = null;
	/** The Image to display in the button */
	protected Image im;
	/** The width and height, based on the Image */
	protected int width, height;
	/** The list of ActionListeners. Usually short, so a Vector is fine.
	 * TODO use new AwtMulticastBroadcaster */
	protected List<ActionListener> l;
	/** Padding around the text for getMinimumSize() */
	protected final int MIN_PAD = 5;
	/** Padding around the text for getPreferredSize() */
	protected final int PREF_PAD = 10;
	/** State: at first CALM; AWAKE when the mouse enters, ARMED if 
	 * the mouse has been clicked but not yet released
	 * in our window; FIRED if activated,
	 */
	protected int state;
	public final static int CALM=0, AWAKE=1, ARMED=2, FIRED=3;
	/** The string form of the URL to jump to */
	String target;
	/** The URL to jump to when the button is pushed. */
	URL targetURL;

	/** Called from the browser to set up. We want to throw various
	 * kinds of exceptions but the API predefines that we don't, so we
	 * limit ourselves to the ubiquitous IllegalArgumentException.
	 */
	public void init() {
		String s;

		System.out.println("In LinkButton::init");
		try {
			if ((target = getParameter("target")) == null)
				throw new IllegalArgumentException("TARGET parameter REQUIRED");
			targetURL = new URL(target);
			if ((imName = getParameter("image")) != null)
				setImage(getImage(new URL(imName)));
		} catch (MalformedURLException rsi) {
			throw new IllegalArgumentException("MalformedURLException " +
				rsi.getMessage());
		}
		label = getParameter("label");

		// last-minute checking: must be an Image or text
		if (imName == null && label == null)
				throw new IllegalArgumentException("LABEL or IMAGE is REQUIRED");
		// Now handle font stuff.
		fontName = getParameter("fontname");
		if ((s = getParameter("fontsize")) != null)
			fontSize = Integer.parseInt(s);
		if (fontName != null || fontSize != 0) {
			System.out.println("Name " + fontName + ", size " + fontSize);
			textFont = new Font(fontName, Font.BOLD, fontSize);
		}
		
		// Applets don't do this; application components may:
		// setSize(getPreferredSize());

		// N.B. Must say we want to handle *mouse* events!
		addMouseListener(this);

		// set up the list of action handlers
		l = new ArrayList<ActionListener>();
	}
	
	public void start() {
		System.out.println("In LinkButton::start");
	}

	/** Give Parameter info the the AppletViewer, just for those
	 * writing HTML without hardcopy documentation :-)
	 */
	public String[][] getParameterInfo() {
		String info[][] = {
			{ "label",		"string",	"Text to display" },
			{ "fontname",	"name",		"Font to display it in" },
			{ "fontsize",	"10-30?",	"Size to display it at" },
			{ "image",		"img file", "Alternate image" },
			{ "target",		"URL",		"Where do you want to go tomorrow?" },
		};
		return info;
	}

	// Font Stuff
	/** The font name */
	protected String fontName = null;
	/** The font size */
	protected int fontSize = 0;
	/** The font itself */
	protected Font textFont;

	/** Set the Image to appear. You must have done the appropriate
	 * (applet or applicatoin) form of getImage before this call.
	 */
	public void setImage(Image i) {
		im = i;
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(im, 0);
		try {
			mt.waitForID(0);
		} catch(InterruptedException e) {
			System.err.println("Wonkey! INTR in waitForID!");
			return;
		}
		if (mt.isErrorID(0)) {
			throw new IllegalArgumentException("Couldn't load image");
		}
		width = im.getWidth(this);
		height = im.getHeight(this);
		setSize(width, height);
	}

	/** Compute the size of a String */
	protected Dimension getStrSize(String l) {
		if (textFont == null) 
			throw new IllegalArgumentException("No Font!");
		FontMetrics fm = getFontMetrics(textFont);
		return new Dimension(fm.stringWidth(l), fm.getAscent());
	}


	/** Paint -- draw the text */
	public void paint(Graphics g) {
		Dimension bd = getSize();
		// System.out.println("In paint, im=" + im + "; label=" + label);

		if (im != null) {
			g.drawImage(im, 0, 0, bd.width, bd.height, this);
			g.draw3DRect(0, 0, bd.width-2, bd.height-2, true);
			return;
		}

		switch(state) {
		case CALM:
			g.setColor(Color.black);
			break;
		case AWAKE:
			g.setColor(Color.blue);
			break;
		case ARMED:
			g.setColor(Color.red);
			break;
		case FIRED:
			g.setColor(Color.green);
			break;
		default:
			showStatus("LinkButton BUG: state=" + state);
		}

		// draw the box
		g.draw3DRect(0, 0, bd.width-2, bd.height-2, true);

		if (label == null)
			return;

		// Compute where to draw the text.
		g.setFont(textFont);
		Dimension td = getStrSize(label);
		int x = (bd.width-td.width)/2;
		if (x<0)	// what if text is too wide? Cram at left margin.
			x=0;
		int y = ((bd.height-td.height)/2)+td.height;
		// System.out.println("drawString("+label+", "+x+", "+y+");");

		// Now draw it.
		g.drawString(label, x, y);
		return;
	}

	/** Add an ActionListener to our list of listeners. */
	public void addActionListener(ActionListener listener) {
		l.add(listener);
	}

	/** Remove an ActionListener; we'll no longer bother it. */
	public void removeActionListener(ActionListener listener) {
		l.remove(listener);
	}

	/** The mouse was clicked, so notify all our ActionListeners. */
	protected void fireButton() {
		repaint();
		for (int i=0; i<l.size(); i++)
			((ActionListener)(l.get(i))).actionPerformed(
				new ActionEvent(this,
					ActionEvent.ACTION_PERFORMED,
					label == null? "A LinkButton" : label));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// nothing to do!
		}
		if (targetURL != null) {
			showStatus("Going to " + target);
			getAppletContext().showDocument(targetURL);
		}
	}

	public void mousePressed(MouseEvent e) {
		state = ARMED;
		repaint();
	}

	/** The mouse was released in our window. If also pressed there,
	 * consider it a "click", and fire the event. */
	public void mouseReleased(MouseEvent e) {
		// System.out.println("MouseUp");

		if (state==ARMED) {
			state = FIRED;
			fireButton();
		}
		state = CALM;
		repaint();
	}

	/** Called by AWT when the mouse walks into our den. */
	public void mouseEntered(MouseEvent e) {
		// System.out.println("mouseEntered");
		state = AWAKE;
		repaint();
		showStatus(target);
	}

	/** Called by AWT when the mouse escapes from our den. */
	public void mouseExited(MouseEvent e) {
		// System.out.println("mouseExited");
		state = CALM;
		repaint();
		showStatus("");
	}

	/** mouseClicked is defined by MouseListener, but not used here */
	public void mouseClicked(MouseEvent e) {
	}
}
