import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** A Sprite is one Image that moves around the screen on its own */
class Sprite extends Component implements Runnable {
	protected static int spriteNumber = 0;
	protected Thread t;
	protected int x, y;
	protected Bounce parent;
	protected Image img;
	protected boolean done = false;

	/** Construct a Sprite with a Bounce parent: construct
	 * and start a Thread to drive this Sprite.
	 */
	Sprite(Bounce parent, Image img) {
		super();
		this.parent = parent;
		this.img = img;
		setSize(img.getWidth(this), img.getHeight(this));
		t = new Thread(this);
		t.setName("Sprite #" + ++spriteNumber);
		t.start();
	}

	/** Stop this Sprites thread. */
	void stop() {
		System.out.println("Stopping " + t.getName());
		done = true;
	}

	/**
	 * Run one Sprite around the screen.
	 * This version is very stupid, and just moves them around
	 * at some 45-degree angle.
	 */
    public void run() {
		int width = parent.getSize().width;
		int height = parent.getSize().height;
		// Random location
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
			// System.out.println("Move " + t.getName() + " from " +
			// 	getLocation() + " to " + x + "," + y);
			setLocation(x, y);
			repaint();
			try {
				Thread.sleep(250);
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

/** This is the main class; create and start Sprites. */
public class Bounce extends Applet implements ActionListener {
	Panel p;
	Image img;
	Vector v;

    public void init() {
		Button b = new Button("Start");
		b.addActionListener(this);
		setLayout(new BorderLayout());
		add("North", b);
		add("Center", p = new Panel());
		p.setLayout(null);
		String imgName = getParameter("imagefile");
		if (imgName == null) imgName = "duke.gif";
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
				"Couldnt load image " + imgName);
		}
		v = new Vector();
    }

    public void actionPerformed(ActionEvent e) {
		System.out.println("Creat-ing another one!");
		Sprite s = new Sprite(this, img);
		p.add(s);
		v.addElement(s);
    }

    public void stop() {
		for (int i=0; i<v.size(); i++) {
			((Sprite)(v.get(i))).stop();
		}
		v.clear();
    }
}

