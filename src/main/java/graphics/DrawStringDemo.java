import java.awt.*;
import javax.swing.*;

public class DrawStringDemo extends JComponent {
	int textX = 10, textY = 20;

	public void paint(Graphics g) {
		g.drawString("Hello Java", textX, textY);
	}

	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
}

