import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;

/** ShowDocApplet: Demonstrate showDocument().
 * @author Ian Darwin, http://www.darwinsys.com/
 */ 
public class ShowDocApplet extends Applet {
	// String targetString = "http://www.darwinsys.com/javacook/secret.html";
	String targetString = "file:///c:/javasrc/network/ShowDocApplet.java";
	/** The URL to go to */
	URL targetURL;

	/** Initialize the Applet */
	public void init() {
		setBackground(Color.gray);
		try {
			targetURL = new URL(targetString);
		} catch (MalformedURLException mfu) {
			throw new IllegalArgumentException(
				"ShowDocApplet got bad URL " + targetString);
		}
		Button b = new Button("View Secret");
		add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAppletContext().showDocument(targetURL);
			}
		});
	}

	public void stop() {
		System.out.println("Ack! Its been fun being an Applet. Goodbye!");
	}
}
