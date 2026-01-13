package threads;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

/** Animator - move a rectangle diaganolly across the screen
 * using a Thread.
 */
public class Animator extends JPanel implements Runnable {
	private static final long serialVersionUID = 1;
	/** Where to draw the moving image: x coordinate */
	int x;
	/** Where to draw the moving image: y coordinate */
	int y;
	/** This is set true to stop the animation thread. */
	boolean done;

	/** Called to start the page */
	public void start() {
		done = false;
		new Thread(this).start();
	}

	/** Called to stop the animation */
	public void stop() {
		done = true;
	}

	/**
	 * Move the rectangle around the screen at a 45-degree angle.
	 * Called by the Thread when there is CPU time available for me.
	 */
    public synchronized void run() {
		// Get the framesize
		int width = getSize().width;
		int height = getSize().height;

		// Start at a random location in it.
		x = (int)(Math.random() * width);
		y = (int)(Math.random() * height);

		while (!done) {
			// Obtain current size, in case resized.
			width = getSize().width;
			height = getSize().height;
			// Did we go off the deep end? :-)
			if (x++ >= width)
				x=0;	// return to shallow end 
			if (y++ >= height)
				y=0;
			repaint();		// Tell AWT to call our paint().
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	/** paint -- just draw our image at its current location */
    public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 10, 10);
    }
}
