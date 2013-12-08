package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

/**
 * PixCanvas - a helper class for PhotoUI, or any other
 * program that needs to store and display Images.
 * Originally written as part of the "Photo manipulation GUI" for JabaDex
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class PixCanvas extends Canvas {
	/**  */
	private static final long serialVersionUID = -3674426011385158770L;
	Image im;
	int wid, ht;

	/** Construct a PixCanvas, given a width and height */
	PixCanvas(int w, int h) {
		setSize(wid=w, ht=h);
	}

	/** Construct a PixCanvas, given an Image name */
	PixCanvas(String fn) {
		setImage(fn);
		setSize(im.getWidth(this), im.getHeight(this));
	}

	/** Set the image to a given filename */
	public void setImage(String fn) {
		if (fn == null)
			return;
		// Only the Application version of getImage shown here
		Image i = Toolkit.getDefaultToolkit().getImage(fn);
		setImage(i);
	}

	/** Set the image to a given Image object */
	public void setImage(Image i) {
		if (i == null)
			return;
		im = i;

		// ----- This part omitted from course notes for brevity -----
		// Use a MediaTracker to show the "best"? way of waiting
		// for an image to load, and how to check for errors.
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(im, 0);
		try {
			mt.waitForID(0);
		} catch(InterruptedException e) {
			throw new IllegalArgumentException(
				"Unexpected InterruptedException");
		}
		if (mt.isErrorID(0)) {
			throw new IllegalArgumentException(
				"Couldn't load image");
		}
		// Now that we know the image has been loaded,
		// it is safe to paint it onto the screen.
		// ----- End of part omitted from course notes for brevity -----
		repaint();
	}

	/** Return how big we'd like to be. If image loaded, use its size.
	 * If not, use 200, 100 (why not?).
	 */
	@Override
	public Dimension getPreferredSize() {
		if (im == null || im.getWidth(this) < 0 || im.getHeight(this) < 0)
			return new Dimension(200, 100);
		return new Dimension(im.getWidth(this), im.getHeight(this));
	}

	/** Actually draw the Image onto the screen */
	@Override
	public void paint(Graphics g) {
		if (im == null) {
			g.setColor(Color.red);
			g.fillRect(0, 0, wid, ht);
		} else
			g.drawImage(im, 0, 0, this);
	}
}
