package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PaintComponent extends JPanel {

	private static final long serialVersionUID = -2197739163018099388L;
	int rectX = 20, rectY = 30;
	int rectWidth = 50, rectHeight = 50;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillRect(rectX, rectY, rectWidth, rectHeight);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
}

