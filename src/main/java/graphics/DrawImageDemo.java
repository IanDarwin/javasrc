import java.awt.*;
import javax.swing.*;

public class DrawImageDemo extends JComponent {

	Image myImage = null;

	public void paint(Graphics g) {
		g.drawImage(myImage, 0, 0, this);
	}
}
