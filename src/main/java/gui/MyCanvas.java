import java.awt.*;

/**
 * Dummy Canvas class, to represent the real main part of an application *
 *
 * @author Ian Darwin, http://www.darwinsys.com
 */

class MyCanvas extends Label {
	int width, height;
	int pad;

	MyCanvas(String l, int w, int h) {
		super(l, Label.CENTER);
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
}
