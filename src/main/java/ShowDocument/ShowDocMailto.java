import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;

/** This little gem shows how to launch the Compose Mail window
 * in a Browser; inspired by a discussion with a student in which
 * I guessed (correctly, it seems) that showDocument() goes through
 * enough "common code" that showDocument() with a "mailto:" URL
 * would actually work...
 @author Ian Darwin
 */
public class ShowDocMailto extends Applet {
	protected Button mailButton;

	public void init() {
		add(mailButton = new Button("Compose..."));
		mailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			try {
				// Get the address from a PARAM...
				String address = getParameter("ADDRESS");
				if (address == null) 
					throw new IllegalArgumentException(
					"ShowDocMailto requires an ADDRESS Parameter in the HTML");

				// Make up the URL object
				URL myNewURL = new URL("mailto:" + address);

				// debug...
				showStatus("URL = " + myNewURL);

				// "And then a miracle occurs..."
				getAppletContext().showDocument(myNewURL);

			} catch (Exception err) {
				System.err.println("Error!\n" + err);
				showStatus("Error, look in Java Console for details!");
			}
			}
		});
	}
}
