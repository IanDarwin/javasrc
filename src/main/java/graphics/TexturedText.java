import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

/** Text with a Texture
 * @author	Ian Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class TexturedText extends JComponent {
	/** The image we draw in the texture */
	protected BufferedImage bim; 
	/** The texture for painting. */
	TexturePaint tp;
	/** The string to draw. */
	String mesg = "Stripey";
	/** The font */
	Font myFont = new Font("Lucida Regular", Font.BOLD, 72);

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a TexturedText object, tell it to show up
		final Frame f = new Frame("TexturedText");
		TexturedText comp = new TexturedText();
		f.add(comp);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		f.pack();
		f.setLocation(200, 200);
		f.setVisible(true);
	}

	protected static Color[] colors = {
		Color.red, Color.blue, Color.yellow,
	};

	/** Construct the object */
	public TexturedText() {
		super();
		setBackground(Color.white);
		int width = 8, height = 8;
		bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bim.createGraphics();
		for (int i=0; i<width; i++) {
			g2.setPaint(colors[(i/2)%colors.length]);
			g2.drawLine(0, i, i, 0);
			g2.drawLine(width-i, height, width, height-i);
		}
		Rectangle r = new Rectangle(0, 0, bim.getWidth(), bim.getHeight());
		tp = new TexturePaint(bim, r);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(tp);
		g2.setFont(myFont);
		g2.drawString(mesg, 20, 100);
	}

	public Dimension getMinimumSize() {
	 	return new Dimension(250, 100);
	}

	public Dimension getPreferredSize() {
	 	return new Dimension(320, 150);
	}
}
