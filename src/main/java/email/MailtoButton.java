import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;

/** 
 * MailtoButton -- look like a mailto, but not visible to spiders.
 *
 * @author	Copyright 1995, 1997 Ian F. Darwin,
 * <A HREF="mailto:ian@darwinsys.com">ian@darwinsys.com</A>,
 * <A HREF="http:www.darwinsys.com/">http://www.darwinsys.com</A>.
 * @version	$Id$
 */
public class MailtoButton extends Applet {
	/** The label that is to appear in the button */
	protected String label = null;
	/** The width and height */
	protected int width, height;
	/** The string form of the URL to jump to */
	protected String target;
	/** The URL to jump to when the button is pushed. */
	protected URL targetURL;
	/** The name of the font */
	protected String fontName;
	/** The font */
	protected Font theFont;
	/** The size of the font */
	protected int fontSize;

	/** Called from the browser to set up. We want to throw various
	 * kinds of exceptions but the API predefines that we don't, so we
	 * limit ourselves to the ubiquitous IllegalArgumentException.
	 */
	public void init() {
		// System.out.println("In LinkButton::init");
		try {
			if ((target = getParameter("target")) == null)
				throw new IllegalArgumentException("TARGET parameter REQUIRED");
			targetURL = new URL(target);
		} catch (MalformedURLException rsi) {
			throw new IllegalArgumentException("MalformedURLException " +
				rsi.getMessage());
		}
		label = getParameter("label");

		// last-minute checking: must be an Image or text
		if (label == null)
				throw new IllegalArgumentException("LABEL is REQUIRED");
		// Now handle font stuff.
		fontName = getParameter("fontname");
		String s;
		if ((s = getParameter("fontsize")) != null)
			fontSize = Integer.parseInt(s);
		if (fontName != null || fontSize != 0) {
			System.out.println("Name " + fontName + ", size " + fontSize);
			theFont = new Font(fontName, Font.BOLD, fontSize);
		}
		
		Button b = new Button(label);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (targetURL != null) {
					// showStatus("Going to " + target);
					getAppletContext().showDocument(targetURL);
				}
			}
		});
		if (theFont != null)
			b.setFont(theFont);
		add(b);
	}
	
	/** Give Parameter info the the AppletViewer, just for those
	 * writing HTML without hardcopy documentation :-)
	 */
	public String[][] getParameterInfo() {
		String info[][] = {
			{ "label",		"string",	"Text to display" },
			{ "fontname",	"name",		"Font to display it in" },
			{ "fontsize",	"10-30?",	"Size to display it at" },
			{ "target",		"URL",		"Where do you want to go tomorrow?" },
		};
		return info;
	}
}
