package graphics;

import java.awt.Container;

import javax.swing.JApplet;
import javax.swing.JLabel;

/** Create a TTFontDemo label in an Applet.
 */
public class TTFontApplet extends JApplet {

	private static final long serialVersionUID = 929012724140365546L;

	/** Initialize Applet GUI */
	public void init() {

		String message = getParameter("message");
		if (message == null)
			message = "TrueType Font Demonstration Applet";

		String fontFileName = getParameter("fontFileName");
		if (fontFileName == null)
			fontFileName = "Kellyag_.ttf";
		Container cp = getContentPane();
		try {
			cp.add(new TTFontDemo(fontFileName, message));
		} catch (Exception ex) {
			cp.add(new JLabel(ex.toString(), JLabel.CENTER));
			ex.printStackTrace();
		}
	}
}
