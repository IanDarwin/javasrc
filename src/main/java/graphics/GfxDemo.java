import java.awt.*;

/**
 * Graphics Demo for Learning Tree Course 471/478
 */
public class GfxDemo extends Frame {
	int width, height;

	GfxDemo(String s) {
		setTitle(s);
		setSize(width=400, height=300);
	}
	GfxDemo(String s, int w, int h) {
		setTitle(s);
		setSize(width=w, height=h);
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, width/2, height/2);
		g.setColor(Color.blue);
		g.drawString("Hello World of Blue", (width/2)+10, (height/2)+10);
	}

	public static void main(String a[]) {
		GfxDemo xyz = new GfxDemo("Default Size");
		xyz.setVisible(true);

		new GfxDemo("Smaller", 100,100).setVisible(true);
	}
}

