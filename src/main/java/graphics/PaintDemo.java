package graphics;

import java.awt.*;

public class PaintDemo extends Component {
	int rectX = 20, rectY = 30;
	int rectWidth = 50, rectHeight = 50;

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(rectX, rectY, rectWidth, rectHeight);
	}

	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
}

