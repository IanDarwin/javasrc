package email;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

/** 
 * MailtoButton -- look like a mailto, but not visible to spiders.
 *
 * @author	Copyright 1995, 1997 Ian F. Darwin,
 * <A HREF="mailto:http://www.darwinsys.com/">http://www.darwinsys.com/</A>,
 * <A HREF="http:www.darwinsys.com/">http://www.darwinsys.com</A>.
 */
// BEGIN main
public class MailtoButton extends Applet {

	private static final long serialVersionUID = -3186706180199804315L;
	/** The label that is to appear in the button */
	protected String label = null;
	/** The width and height */
	protected int width, height;
	/** The string form of the URL to jump to */
	protected String targetName, targetHost;
	/** The URL to jump to when the button is pushed. */
	protected URL targetURL;
	/** The name of the font */
	protected String fontName;
	protected String DEFAULTFONTNAME = "helvetica";
	/** The font */
	protected Font theFont;
	/** The size of the font */
	protected int fontSize = 18;
	/** The HTML PARAM for the user account -- keep it short */
	private String TARGET1 = "U";	// for User 
	/** The HTML PARAM for the hostname -- keep it short */
	private String TARGET2 = "H";	// for Host 
	// Dummy
	//private String BOGON1 = "username";	// happy strings-ing, SPAM perps
	//private String BOGON2 = "hostname";	// ditto.
	/** The string for the Subject line, if any */
	private String subject;

	/** Called from the browser to set up. We want to throw various
	 * kinds of exceptions but the API predefines that we don't, so we
	 * limit ourselves to the ubiquitous IllegalArgumentException.
	 */
	public void init() {
		// System.out.println("In LinkButton::init");
		try {
			if ((targetName = getParameter(TARGET1)) == null)
				throw new IllegalArgumentException(
					"TARGET parameter REQUIRED");
			if ((targetHost = getParameter(TARGET2)) == null)
				throw new IllegalArgumentException(
					"TARGET parameter REQUIRED");

			String theURL = "mailto:" + targetName + "@" + targetHost;

			subject = getParameter("subject");
			if (subject != null)
				theURL += "?subject=" + subject;

			targetURL = new URL(theURL);

		} catch (MalformedURLException rsi) {
			throw new IllegalArgumentException("MalformedURLException " +
				rsi.getMessage());
		}


		label = getParameter("label");	// i.e., "Send feedback"
		if (label == null)
				throw new IllegalArgumentException("LABEL is REQUIRED");

		// Now handle font stuff.
		fontName = getParameter("font");
		if (fontName == null)
			fontName = DEFAULTFONTNAME;
		String s;
		if ((s = getParameter("fontsize")) != null)
			fontSize = Integer.parseInt(s);
		if (fontName != null || fontSize != 0) {
			// System.out.println("Name " + fontName + ", size " + fontSize);
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
	
	/** Give Parameter info to the AppletViewer, just for those
	 * writing HTML without hardcopy documentation :-)
	 */
	public String[][] getParameterInfo() {
		String info[][] = {
			{ "label",		"string",	"Text to display" },
			{ "fontname",	"name",		"Font to display it in" },
			{ "fontsize",	"10-30?",	"Size to display it at" },

			// WARNING - these intentionally lie, to mislead spammers who
			// are incautious enough to download and run (or strings) the
			// .class file for this Applet.

			{ "username",	"email-account",
				"Where do you want your mail to go today? Part 1" },
			{ "hostname",	"host.domain",
				"Where do you want your mail to go today? Part 2" },
			{ "subject",	"subject line",
				"What your Subject: field will be." },
		};
		return info;
	}
}
// END main
