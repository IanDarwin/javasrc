import java.awt.*;
import java.applet.*;
import java.net.*;

/**
 * Try running a CGI-BIN script from within Java.
 */
public class TryCGI extends Applet {
	protected Button goButton;

	public void init() {
		add(goButton = new Button("Go for it!"));
	}

	public boolean action(Event evt, Object o) {
		try {
			URL myNewURL = new URL(
				"http://server/cgi-bin/credit");

			// debug...
			System.out.println("URL = " + myNewURL);

			// "And then a miracle occurs..."
			getAppletContext().showDocument(myNewURL);

		} catch (Exception err) {
			System.err.println("Error!\n" + err);
			showStatus("Error, look in Java Console for details!");
		}
		return true;	// NOTREACHED
	}
}
