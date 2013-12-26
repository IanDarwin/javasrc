package threads;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/** This is the Bounce class; create and start Sprites, using Threads. */
// BEGIN main
public class Bounce extends Applet implements ActionListener {

	private static final long serialVersionUID = -5359162621719520213L;
	/** The main Panel */
	protected Panel p;
	/** The image, shared by all the Sprite objects */
	protected Image img;
	/** A Vector of Sprite objects. */
	protected List<Sprite> v;

    public void init() {
		Button b = new Button("Start");
		b.addActionListener(this);
		setLayout(new BorderLayout());
		add(b, BorderLayout.NORTH);
		add(p = new Panel(), BorderLayout.CENTER);
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
				"Couldn't load image " + imgName);
		}
		v = new Vector<Sprite>(); // multithreaded, use Vector
    }

    public void actionPerformed(ActionEvent e) {
		System.out.println("Creat-ing another one!");
		Sprite s = new Sprite(this, img);
		s.start();
		p.add(s);
		v.add(s);
    }

    public void stop() {
		for (int i=0; i<v.size(); i++) {
			v.get(i).stop();
		}
		v.clear();
    }
}

// END main
