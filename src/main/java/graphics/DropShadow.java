import java.applet.*;
import java.awt.*;

/** 
 * DropShad -- show overlapped painting.
 *
 * @author	Copyright 1995, 1997 Ian F. Darwin,
 * <A HREF="mailto:ian@darwinsys.com">ian@darwinsys.com</A>,
 * <A HREF="http:www.darwinsys.com/">http://www.darwinsys.com</A>.
 * @version	$Id$
 */
public class DropShad extends Applet {
	/** The label that is to appear in the window */
	protected String theLabel = null;
	/** The width and height */
	protected int width, height;
	/** The name of the font */
	protected String fontName;
	/** The font */
	protected Font theFont;
	/** The size of the font */
	protected int fontSize = 18;
	/** The offset for the drop shadow */
	protected int theOffset = 3;
	/** True if we got all required parameters */
	protected boolean inittedOK = false;

	/** Called from the browser to set up. We want to throw various
	 * kinds of exceptions but the API predefines that we don't, so we
	 * limit ourselves to the ubiquitous IllegalArgumentException.
	 */
	public void init() {
		// System.out.println("In DropShad::init");

		theLabel = getParameter("label");
		if (theLabel == null)
				throw new IllegalArgumentException("LABEL is REQUIRED");
		// Now handle font stuff.
		fontName = getParameter("fontname");
		if (fontName == null)
				throw new IllegalArgumentException("FONTNAME is REQUIRED");
		String s;
		if ((s = getParameter("fontsize")) != null)
			fontSize = Integer.parseInt(s);
		if (fontName != null || fontSize != 0) {
			theFont = new Font(fontName, Font.BOLD + Font.ITALIC, fontSize);
			System.out.println("Name " + fontName + ", font " + theFont);
		}
		if ((s = getParameter("offset")) != null)
			theOffset = Integer.parseInt(s);
		setBackground(Color.green);
		inittedOK = true;
	}

	/** Paint method showing drop shadow effect */
	public void paint(Graphics g) {
		if (!inittedOK)
			return;
		g.setFont(theFont);
		g.setColor(Color.black);
		g.drawString(theLabel, theOffset+30, theOffset+50);
		g.setColor(Color.white);
		g.drawString(theLabel, 30, 50);
	}
	
	/** Give Parameter info to the AppletViewer, just for those
	 * writing HTML without hardcopy documentation :-)
	 */
	public String[][] getParameterInfo() {
		String info[][] = {
			{ "label",		"string",	"Text to display" },
			{ "fontname",	"name",		"Font to display it in" },
			{ "fontsize",	"10-30?",	"Size to display it at" },
		};
		return info;
	}
}
