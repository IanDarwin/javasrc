import java.awt.*;
import javax.swing.*;

/**
 * Dummy Canvas class, to represent the real main part of an application *
 *
 * @author Ian Darwin, http://www.darwinsys.com
 */

class MyCanvas extends JLabel {
	protected int width, height;
	protected int pad;
	protected Color col = Color.white;

	MyCanvas(String l, int w, int h) {
		super(l, JLabel.CENTER);
		width = w; height = h;
	}
	MyCanvas(String l, int w, int h, Color c) {
		this(l, w, h);
		setBackground(c);
	}

	public Dimension getMinimumSize() {
		return new Dimension(width, height);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width+pad, height+pad);
	}

	public void setColor(Color c) {
		col = c;
		repaint();
	}

	public void paint(Graphics g) {
		Dimension d = getSize();
		g.setColor(col);
		g.fillRect(0, 0, d.width-1, d.height-1);
	}
}
