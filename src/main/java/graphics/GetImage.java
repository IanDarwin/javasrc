package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JFrame;

/** This program, which can be an Applet or an Application,
 * shows a form of Toolkit.getImage().
 * <p>
 * For Application, just run it (has own main).
 */
// tag::main[]
public class GetImage extends JPanel {

	private static final long serialVersionUID = 4288395022095915666L;
	private Image image;

	public void GetImage() {
		loadImage();
	}

	public void loadImage() {

		// Portable version: getClass().getResource() 
		URL url = getClass().getResource("Duke.gif");
		image = getToolkit().getImage(url);
		// Or just:
		// image = getToolkit().getImage(getClass().getResource("Duke.gif"));
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 20, 20, this);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("GetImage");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GetImage myPanel = new GetImage();
		f.setContentPane(myPanel);
		f.setSize(100, 100);
		f.setVisible(true);
	}
}
// end::main[]
