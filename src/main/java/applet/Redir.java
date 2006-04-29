package applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.net.URL;

/** A simple redirection applet.
 * @author Ian Darwin
 */
public class Redir extends Applet implements Runnable {

	private static final long serialVersionUID = -778316706081946913L;
	protected String urlString;
	protected URL theNewURL;
	protected final static int NSECONDS = 5;
	protected Thread t;

	public void init() {
		try {
			// Get the address from a PARAM...
			urlString = getParameter("URL");
			if (urlString == null)  {
				urlString = "MISSING URL";
				throw new IllegalArgumentException(
				"Redir requires a URL parameter in the HTML");
			}

			// Make up the URL object
			theNewURL = new URL(urlString);

			// debug...
			// showStatus("URL = " + theNewURL);

		} catch (Exception err) {
			System.err.println("Error!\n" + err);
			showStatus("Error, look in Java Console for details!");
		}
	}

	public void start() {
		if (theNewURL == null)
			return;

		t = new Thread(this);
		t.start();
	}

	/** Print a little message to the user. */
	public void paint(Graphics g) {
		if (urlString != null)
			g.drawString(urlString, 20, 50);
		else
			g.drawString("Initializing...", 20, 50);
	}

	/** If users moves off the page, set Thread t to null so
	 * we don't showDocument from within the middle of the new page!
	 */
	public void stop() {
		t = null;
	}

	/** run, called by the Thread, does the work of sleeping
	 * for a fixed number of seconds then, if the user hasn't
	 * moved off the page, actually passing control to the new page.
	 */
	public void run() {
		for (int i=NSECONDS; i>=0; i--) {
			try {
				Thread.sleep(1000);
				if (t == null)
					return;
			} catch (InterruptedException e) {
				// so what?
			}
			if (t == null)
				return;
			showStatus(Integer.toString(i));

			if (t == null)
				return;

			showStatus("Ignition!");
			// "And then a miracle occurs..."
			getAppletContext().showDocument(theNewURL);
		}
	}
}
