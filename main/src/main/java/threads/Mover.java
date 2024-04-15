package threads;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Mover -- move an image, slowly.
 * @author Ian Darwin, https://darwinsys.com/
 */
public class Mover extends JPanel implements Runnable {
	private static final long serialVersionUID = 1;
	/** The done or not done flag */
	protected volatile boolean done = false;
	/** The current Image, or null */
	protected Image img;
	/** The NAME of the current Image, or null */
	protected String imageName;
	/** the size of the current image */
	protected int imgWid = 0, imgHt = 0;
	/** DEFAULT msec between updates */
	public final static int DEFAULT_INTERVAL = 40;
	/** msec between updates */
	protected int interval = DEFAULT_INTERVAL;
	/** Where we are */
	protected int offset = 0;
	/** The Thread that keeps us ticking */
	protected Thread ticker;

	/** Construct a Mover, given an Image and using a default pause interval. */
	public Mover(String imgName) {
		this(imgName, DEFAULT_INTERVAL);
	}

	/** Construct a Mover, given an Image and a pause interval. */
	public Mover(String imgName, int pauseInt) {
		interval = pauseInt;
		imageName = imgName;
		init();
	}

	/** Since we have the above Constructors, we need this one for some uses */
	public Mover() {
		init();
	}

	/** Setup a Mover */
	public void init() {
		System.out.println("imageName = " + imageName);
		setImage(imageName);
		System.out.println("setImage done");
	}

	public void start() {
		done = false;
		startThread();
	}

	protected void startThread() {
		ticker = new Thread(this);
		ticker.setName("Ticker animation");
		ticker.setPriority(Thread.MAX_PRIORITY);
		ticker.start();
	}

	public void stop() {
		done = true;
		ticker = null;
	}

	/** Set the image to the given file */
	public void setImage(String fn) {
		if (fn == null)
			return;

		imgWid = imgHt = 0;
		offset = 0;

		img = Toolkit.getDefaultToolkit().getImage(fn);

		// Use a MediaTracker to show the "best"? way of waiting
		// for an image to load, and how to check for errors.
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img, 0);
		try {
			mt.waitForID(0);
		} catch(InterruptedException e) {
			throw new IllegalArgumentException(
				"Unexpected InterruptedException");
		}
		if (mt.isErrorID(0)) {
			throw new IllegalArgumentException(
				"Couldn't load image " + fn);
		}
	}

	/** Return how big we'd like to be. If image loaded, use its size.
	 * If not, use an arbitrary default.
	 */
	public Dimension getPreferredSize() {
		if (img == null || img.getWidth(this) < 0 || img.getHeight(this) < 0)
			return new Dimension(100, 100);
		return new Dimension(imgWid * 20, imgHt);
	}

	/** Pick a new position for the image, and ask it to be painted */
	public void run() {
		int w = getSize().width;
		// System.out.println("Width = " + w);
		while (!done) {
			if (offset++ > w)
				offset = 0;
			try {
				Thread.sleep(interval);
				repaint();
			} catch (InterruptedException canthappen) {
			}
		}
	}

	/** Actually draw the Image onto the screen */
	public void paint(Graphics g) {
		if (img == null) {
			g.setColor(Color.red);
			g.fillRect(0, 0, getSize().width, getSize().height);
		} else
			g.drawImage(img, offset, 0, this);
	}

	/** "main program" method - construct and show */
	public static void main(String[] av) {
	
		var f = new JFrame("Mover Demo");
		final Mover m = new Mover("mover.gif", 10);
		f.add(m);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.init();
		m.start();
		f.pack();
		f.setVisible(true);
	}
}
