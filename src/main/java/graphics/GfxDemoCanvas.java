import java.awt.*;

/**
 * Fancier, Canvas-based Graphics Demo for Learning Tree Course 471/478
 * This subclass of Canvas does the drawing;
 * GfxDemo2 merely creates a Frame and adds the Canvas to it,
 * while PrintDemoGFx creates one of these and calls its paint()
 * method with a Printer Graphics object.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class GfxDemoCanvas extends Canvas {
	int width, height;

	GfxDemoCanvas(int w, int h) {
		setSize(width=w, height=h);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paint(Graphics g) {
		width = getSize().width;
		height = getSize().height;
		g.setColor(Color.black);
		g.drawRect(0, 0, width-1, height-1);
		g.setColor(Color.red);
		g.fillRect(0, 0, width/2, height/2);
		g.setColor(Color.blue);
		g.drawString("Welcome to My Blue Heaven", (width/2)+10, (height/2)+10);
	}
}
