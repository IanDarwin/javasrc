import java.awt.*;
import java.net.*;

/**
 * Standalone Image Viewer - works with any AWT-supported format.
 */
public class ImageView extends Frame {
	/** The Image object */
	Image im;
	/** Size of the image */
	int width, height;

	/** Construct an ImageView viewer, given a filename. */
	ImageView(String s) {
		super("ImageView: " + s);
		URL url = getClass().getResource(s);
		// System.out.println(url);
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
			System.err.println("Couldn't load image file " + s);
			return;
		}

		// Now that we know the image has been loaded,
		// it is safe to take its width and height.
		// ----- End of part omitted from course notes for brevity -----
		width = im.getWidth(this);
		height = im.getHeight(this);
		setSize(width, height);
	}

	public void paint(Graphics g) {
		g.drawImage(im, 0, 0, this);
	}

	public static void main(String arg[]) {
		if (arg.length == 0)
			System.err.println("Usage: ImageView file");
		else
			for (int i=0; i<arg.length; i++)
				new ImageView(arg[i]).setVisible(true);
	}
}
