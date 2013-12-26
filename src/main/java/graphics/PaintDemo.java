package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

// BEGIN main
public class PaintDemo extends Component {

	private static final long serialVersionUID = -5595189404659801913L;
	int rectX = 20, rectY = 30;
	int rectWidth = 50, rectHeight = 50;

	/**
	 * Component subclasses can override paint(), but
	 * JComponent subclasses should normally use paintComponent()
	 * instead, to avoid clobbering border painting and the like.
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(rectX, rectY, rectWidth, rectHeight);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
}

// END main
