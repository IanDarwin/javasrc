package threads;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

// tag::main[]
/** A Sprite is one Image that moves around the screen on its own */
public class Sprite extends Component implements Runnable {
	private static final long serialVersionUID = 1L;
	protected static int spriteNumber = 0;
	protected int number;
	protected int x, y;
	protected Component parent;
	protected Image img;
	protected volatile boolean done = false;
	/** The time in mSec to pause between each move. */
	protected volatile int sleepTime = 250;
	/** The direction for this particular sprite. */
	protected Direction direction;
	enum Direction {
		VERTICAL, HORIZONTAL, DIAGONAL
	}
	/** Construct a Sprite with a Component parent, image and direction.
	 * Construct and start a Thread to drive this Sprite.
	 */
	public Sprite(Component parent, Image img, Direction dir) {
		this.parent = parent;
		this.img = img;
		this.number = Sprite.spriteNumber++;
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
		this(parent, img, Direction.DIAGONAL);
	}

	/** Stop this Sprite. */
	public void stop() {
		System.out.println("Stopping " + number);
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
				case DIAGONAL:
					// Let it wrap around
					break;
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
// end::main[]
