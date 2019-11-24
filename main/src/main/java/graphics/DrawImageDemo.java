package graphics;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

public class DrawImageDemo extends JComponent {

	private static final long serialVersionUID = -4904977476532496804L;

	Image myImage = null;

	@Override
	public void paint(Graphics g) {
		g.drawImage(myImage, 0, 0, this);
	}
}
