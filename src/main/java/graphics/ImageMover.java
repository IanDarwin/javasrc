import java.applet.Applet;
import java.awt.Image;
import java.awt.MediaTracker;

/** ImageMover: Move one image around */
public class ImageMover extends Applet {
	/** The image to display */
	protected Image img;
	/** The Sprite containing (and moving) the Image */
	protected Sprite s;

	/** Initialize the applet */
    public void init() {
		// Turn off layout so the Sprite can move at will.
		setLayout(null);
		String imgName = getParameter("imagefile");
		if (imgName == null) 
			throw new IllegalArgumentException(
				"imagefile parameter required");
		String orientation = getParameter("orientation");
		if (orientation == null) 
			throw new IllegalArgumentException(
				"orientation parameter required");
		int orient = 0;
		if (orientation.equalsIgnoreCase("horizontal"))
			orient = Sprite.HORIZONTAL;
		else if (orientation.equalsIgnoreCase("vertical"))
			orient = Sprite.VERTICAL;
		else
			orient = Sprite.DIAGONAL;

		// Create the Image
		img = getImage(getCodeBase(), imgName);
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img, 0);
		try {
			mt.waitForID(0);
		} catch(InterruptedException e) {
			throw new IllegalArgumentException(
				"InterruptedException while loading image " + imgName);
		}
		if (mt.isErrorID(0)) {
			throw new IllegalArgumentException(
				"Couldn't load image " + imgName);
		}
		s = new Sprite(this, img, orient);
		add(s);

		// Now see if the sleeptime was specified */
		String sleep = getParameter("sleeptime");
		if (sleep != null) {
			try {
				int n = Integer.parseInt(sleep);
				s.setSleepTime(n);
			} catch(IllegalArgumentException ex) {
				showStatus("Invalid sleep time number in " + sleep);
			}
		}
    }

	/** The browser wants to start this page; start the Sprite */
	public void start() {
		s.start();
	}

	/** The browser wants to stop this page; stop the Sprite */
    public void stop() {
		s.stop();
    }
}
