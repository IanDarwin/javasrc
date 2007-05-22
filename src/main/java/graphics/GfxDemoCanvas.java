package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Simple demo of drawing into a Component.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class GfxDemoCanvas extends JComponent {

	private static final long serialVersionUID = -6695483451019630133L;
	int width, height;

	public GfxDemoCanvas(int w, int h) {
		setSize(width=w, height=h);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;
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
