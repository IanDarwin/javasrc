import java.awt.*;

/** Finds and displays available fonts
 * @author	Ian Darwin (original)
 * @author	Madhu Siddalangiah (revised)
 */
public class FontDemo extends Canvas {
	/** The list of Fonts */
	String fontList[];
	/** How much space between each name */
	static final int YINCR = 20;

	/** Construct a FontDemo -- Sets title and gets 
	 * array of fonts on the system
	 */
	public FontDemo() {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// For JDK 1.1: returns about 10 names (Serif, SansSerif, etc.)
		// fontList = toolkit.getFontList();
		// For JDK 1.2: a much longer list; most of the names that come
		// with your OS (e.g., Arial, Lucida, Lucida Bright, Lucida Sans...)
		fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getAvailableFontFamilyNames();

		setSize(250, fontList.length * YINCR);
	}

	/** Draws the font names in its font.
	 * Called by the runtime when painting is needed
	 */
	public void paint(Graphics g) {
		for (int i=0; i<fontList.length; i+=1) {
			Font font = new Font(fontList[i], Font.BOLD, 14);
			g.setFont(font);
			int x = 20;
			int y = 20 + (YINCR * i);
			g.drawString(fontList[i], x, y);
		}
	}

	/** Simple main program to start it running */
	public static void main(String[] args) {
		Frame f = new Frame("Font Demo");
		f.add(new FontDemo());
		f.setVisible(true);
	}
}
