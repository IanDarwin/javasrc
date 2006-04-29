package applet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Try running a CGI-BIN script from within Java.
 */
@SuppressWarnings("serial")
public class TryCGI extends Applet implements ActionListener {
	protected Button goButton;

	public void init() {
		add(goButton = new Button("Go for it!"));
		goButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			URL myNewURL = new URL("http://server/cgi-bin/credit");

			// debug...
			System.out.println("URL = " + myNewURL);

			// "And then a miracle occurs..."
			getAppletContext().showDocument(myNewURL);

		} catch (Exception err) {
			System.err.println("Error!\n" + err);
			showStatus("Error, look in Java Console for details!");
		}
	}
}
