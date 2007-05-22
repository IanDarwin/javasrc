package graphics;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Standalone Image Viewer - works with any AWT-supported format.
 */
public class ImageView extends JComponent {

	private static final long serialVersionUID = -7547320904301255665L;
	/** The Image object */
	protected Image im;
	/** Size of the image */
	protected int width, height;
	/** The graphical component */
	protected Container cp;
	/** The name of the image file */
	protected String fileName;

	/** Construct an ImageView viewer, given a filename. */
	public ImageView(String fileName) {
		this.fileName = fileName;
		cp = this;

	}

	public void loadImage() {

		URL url = getClass().getResource(fileName);
		im = Toolkit.getDefaultToolkit().getImage(url);

		// ----- This part omitted from course notes for brevity -----
		// Use a MediaTracker to show the "best"? way of waiting
		// for an image to load, and how to check for errors.
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(im, 0);
		try {
			mt.waitForID(0);
		} catch(InterruptedException e) {
			System.err.println("Unexpected interrupt in waitForID!");
			return;
		}
		if (mt.isErrorID(0)) {
			System.err.println("Couldn't load image file " + fileName);
			return;
		}

		// Now that we know the image has been loaded,
		// it is safe to take its width and height.
		// ----- End of part omitted from course notes for brevity -----
		width = im.getWidth(this);
		height = im.getHeight(this);
		setSize(width, height);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(im, 0, 0, this);
	}

	public static void main(String[] arg) {
		if (arg.length == 0) {
			System.err.println("Usage: ImageView file [...]");
		} else {
			for (int i=0; i<arg.length; i++) {
				JFrame jf = new JFrame("ImageView: " + arg[i]);
				ImageView iv = new ImageView(arg[i]);
				jf.getContentPane().add(iv);
				iv.loadImage();
				jf.setSize(iv.getSize());
				jf.setVisible(true);
			}
		}
	}
}
