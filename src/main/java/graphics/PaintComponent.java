import java.awt.*;
import javax.swing.*;

public class PaintComponent extends JPanel {
	int rectX = 20, rectY = 30;
	int rectWidth = 50, rectHeight = 50;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillRect(rectX, rectY, rectWidth, rectHeight);
	}

	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
}

