import java.awt.*;
import java.io.*;
import javax.swing.*;

/** Demo of making TrueType font usable in Java. This is a way cool facility
 * because it means you can have "application-specific" fonts in Java;
 * your application can have its own distinctive font that the user does
 * NOT have to install into the JRE before you can use it.
 * (of course they can install it if they have privileges and want to).
 * <p>
 * Must remain Swing-based despite problems on older systems, since
 * apparently only Swing components can use TTF fonts in this implementation.
 * <p>
 * Did NOT work for me in Applet nor JApplet due to
 * security problems (requires to create a temp file). Could be made
 * to work by providing a policy file.
 * @author	Ian Darwin
 * @since 1.3
 */
public class TTFontDemo extends JLabel {

	/** Construct a TTFontDemo -- Create a Font from TTF.
	 */
	public TTFontDemo(String fontFileName, String text)
	throws IOException, FontFormatException {
		super(text, JLabel.CENTER);

		setBackground(Color.white);

		// First, see if we can load the font file.
		InputStream is = this.getClass().getResourceAsStream(fontFileName);
		if (is == null) {
			throw new IOException("Cannot open " + fontFileName);
		}

		// createFont makes a 1-point font, bit hard to read :-)
		Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, is);

		// So scale it to 24 pt.
		Font ttfReal = ttfBase.deriveFont(Font.PLAIN, 24);

		setFont(ttfReal);
	}

	/** Simple main program for TTFontDemo */
	public static void main(String[] args) throws Exception {

		String DEFAULT_MESSAGE = 
			"What hath man wrought? Or at least rendered?";
		String DEFAULT_FONTFILE =
			"Kellyag_.ttf";
		String message = args.length == 1 ? args[0] : DEFAULT_MESSAGE;
		JFrame f = new JFrame("TrueType Font Demo");

		TTFontDemo ttfd = new TTFontDemo(DEFAULT_FONTFILE, message);
		f.getContentPane().add(ttfd);

		f.setBounds(100, 100, 700, 250);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
