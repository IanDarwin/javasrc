import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Animator extends Applet implements Runnable {
	int x;
	int y;
	boolean done;

    public void init() {
		// nothing to do
	}

	public void start() {
		done = false;
		new Thread(this).start();
	}

	public void stop() {
		done = true;
	}

	/**
	 * Move the rectangle around the screen at a 45-degree angle.
	 */
    public synchronized void run() {
		int width = getSize().width;
		int height = getSize().height;
        x = (int)(Math.random() * width);
        y = (int)(Math.random() * height);
		while (!done) {
			width = getSize().width;
			height = getSize().height;
			if (x++ >= width)
				x=0;
			if (y++ >= height)
				y=0;
			repaint();
			try {
				wait(250);
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
