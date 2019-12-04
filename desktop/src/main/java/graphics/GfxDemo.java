package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * Graphics Demo
 * @author Ian Darwin, originally done for Learning Tree Course 471/478
 */
public class GfxDemo extends JFrame {

	private static final long serialVersionUID = -1379891239436602460L;
	private int width, height;

	GfxDemo(String s) {
		this(s, 400, 300);
	}

	GfxDemo(String s, int w, int h) {
		setTitle(s);
		setSize(width=w, height=h);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, width/2, height/2);
		g.setColor(Color.blue);
		g.drawString("Hello World of Blue", (width/2)+10, (height/2)+10);
	}

	public static void main(String[] a) {

		GfxDemo xyz = new GfxDemo("Default Size");
		xyz.setVisible(true);

		new GfxDemo("Smaller", 100,100).setVisible(true);
	}
}

