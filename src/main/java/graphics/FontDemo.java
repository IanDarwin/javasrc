import java.awt.*;
import javax.swing.*;

/** Finds and displays available fonts
 * <p>
 * TODO: should be a JTable with the text name in one column and the demo
 * in a JLabel in the other.
 * @author	Ian Darwin (original)
 */
public class FontDemo extends JComponent {
	/** The list of Fonts */
	protected String[] fontNames;
	/** The fonts themselves */
	protected Font[] fonts;
	/** How much space between each name */
	static final int YINCR = 20;

	/** Construct a FontDemo -- Sets title and gets 
	 * array of fonts on the system
	 */
	public FontDemo() {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// For JDK 1.1: returns about 10 names (Serif, SansSerif, etc.)
		// fontNames = toolkit.getFontList();
		// For JDK 1.2: a much longer list; most of the names that come
		// with your OS (e.g., Arial, Lucida, Lucida Bright, Lucida Sans...)
		fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getAvailableFontFamilyNames();
		fonts = new Font[fontNames.length];
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, fontNames.length * YINCR);
	}

	/** Draws the font names in its font.
	 * Called by AWT when painting is needed
	 * Does lazy evaluation of Font creation, caching the results
	 * (without this, scrolling performance suffers even on a P3-750).
	 */
	public void paint(Graphics g) {
		for (int i=0; i<fontNames.length; i+=1) {
			if (fonts[i] == null) {
				fonts[i] = new Font(fontNames[i], Font.BOLD, 14);
			}
			g.setFont(fonts[i]);
			int x = 20;
			int y = 20 + (YINCR * i);
			g.drawString(fontNames[i], x, y);
		}
	}

	/** Simple main program to start it running */
	public static void main(String[] args) {
		JFrame f = new JFrame("Font Demo");
		f.getContentPane().add(new JScrollPane(new FontDemo()));
		f.setSize(600, 700);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
