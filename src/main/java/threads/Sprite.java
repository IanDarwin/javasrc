package threads;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

// BEGIN main
/** A Sprite is one Image that moves around the screen on its own */
public class Sprite extends Component implements Runnable {
	protected static int spriteNumber = 0;
	protected Thread t;
	protected int x, y;
	protected Component parent;
	protected Image img;
	protected volatile boolean done = false;
	/** The time in mSec to pause between each move. */
	protected volatile int sleepTime = 250;
	/** The direction for this particular sprite. */
	protected int direction;
	/** The direction for going across the page */
	public static final int HORIZONTAL = 1;
	/** The direction for going up and down */
	public static final int VERTICAL = 2;
	/** The direction for moving diagonally */
	public static final int DIAGONAL = 3;

	/** Construct a Sprite with a Component parent, image and direction.
	 * Construct and start a Thread to drive this Sprite.
	 */
	public Sprite(Component parent, Image img, int dir) {
		this.parent = parent;
		this.img = img;
		switch(dir) {
			case VERTICAL: case HORIZONTAL: case DIAGONAL:
				direction = dir;
				break;
			default:
				throw new IllegalArgumentException(
					"Direction " + dir + " invalid");
		}
		setSize(img.getWidth(this), img.getHeight(this));
	}

	/** Construct a sprite with the default direction */
	public Sprite(Component parent, Image img) {
		this(parent, img, DIAGONAL);
	}

	/** Start this Sprite's thread. */
	public void start() {
		t = new Thread(this);
		t.setName("Sprite #" + ++spriteNumber);
		t.start();
	}

	/** Stop this Sprite's thread. */
	public void stop() {
		if (t == null)
			return;
		System.out.println("Stopping " + t.getName());
		done = true;
	}

	/** Adjust the motion rate */
	protected void setSleepTime(int n) {
		sleepTime = n;
	}

	/**
	 * Run one Sprite around the screen.
	 * This version just moves them around either across, down, or
	 * at some 45-degree angle.
	 */
    public void run() {
		int width = parent.getSize().width;
		int height = parent.getSize().height;
		// Set initial location
		x = (int)(Math.random() * width);
		y = (int)(Math.random() * height);
		// Flip coin for x & y directions
		int xincr = Math.random()>0.5?1:-1;
		int yincr = Math.random()>0.5?1:-1;
		while (!done) {
			width = parent.getSize().width;
			height = parent.getSize().height;
			if ((x+=xincr) >= width)
				x=0;
			if ((y+=yincr) >= height)
				y=0;
			if (x<0)
				x = width;
			if (y<0)
				y = height;
			switch(direction) {
				case VERTICAL: 
					x = 0;
					break;
				case HORIZONTAL: 
					y = 0;
					break;
				case DIAGONAL: break;
			}
			//System.out.println("from " + getLocation() + "->" + x + "," + y);
			setLocation(x, y);
			repaint();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	/** paint -- just draw our image at its current location */
    public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
    }
}
// END main
