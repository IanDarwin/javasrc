package graphics;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.darwinsys.util.Debug;

/**
 * Demo of tiling an Image across a component; draw the image repeatedly
 * in the paint() method.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class TiledImageComponent extends JComponent {

	private static final long serialVersionUID = -8771306833824134974L;
	protected TextField nameTF, passTF, domainTF;
	protected Image im;
	public static final String DEFAULT_IMAGE_NAME =
		"graphics/background.gif";

	/** Set things up nicely. */
	public TiledImageComponent() {

		setLayout(new FlowLayout());
		add(new Label("Name:", Label.CENTER));
		add(nameTF=new TextField(10));

		add(new Label("Password:", Label.CENTER));
		add(passTF=new TextField(10));
		passTF.setEchoChar('*');

		add(new Label("Domain:", Label.CENTER));
		add(domainTF=new TextField(10));

		im = getToolkit().getImage(DEFAULT_IMAGE_NAME);
	}

	/** paint()  - just tile the background.  */
	@Override
	public void paintComponent(Graphics g) {
		if (im == null)
			return;
		int iw = im.getWidth(this), ih=im.getHeight(this);
		if (iw < 0 || ih < 0)	// image not ready
			return;				// live to try again later.
		int w = getSize().width, h = getSize().height;

		for (int i = 0; i<=w; i+=iw) {
			for (int j = 0; j<=h; j+=ih) {
				Debug.println("draw", "drawImage(im,"+i+","+j+")");
				g.drawImage(im, i, j, this);
			}
		}
	}

	public static void main(String[] av) {
		JFrame f = new JFrame("TiledImageComponent Demo");
		f.getContentPane().add(new TiledImageComponent());
		f.setSize(200, 200);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

// END main
