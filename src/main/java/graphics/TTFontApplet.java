import java.applet.*;
import java.awt.Container;
import javax.swing.*;

/** Create a TTFontDemo label in an Applet.
 * @version $Id$
 */
public class TTFontApplet extends JApplet {

	/** Initialize the GUI */
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
