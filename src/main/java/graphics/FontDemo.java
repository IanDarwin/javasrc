import java.awt.*;

/** Finds and displays available fonts
 * @author	Ian Darwin (original)
 * @author	Madhu Siddalangiah (revised)
 */
public class FontDemo extends Frame {

	String fontList[];

	/** Construct a FontDemo -- Sets title and gets 
	 * array of fonts on the system
	 */
	public FontDemo() {
		setTitle("Font Demo");

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		fontList = toolkit.getFontList();

		setSize(150, 200);
	}

	/** Draws the font names in its font.
	 * Called by the runtime when painting is needed
	 */
	public void paint(Graphics g) {
		for (int i=0; i<fontList.length; i+=1) {
			Font font = new Font(fontList[i], Font.BOLD, 14);
			g.setFont(font);
			int x = 20;
			int y = 20 + (20 * i);
			g.drawString(fontList[i], x, y);
		}
	}

	/** Simple main program to start it running */
	public static void main(String args[]) {
		new FontDemo().setVisible(true);
	}
}
