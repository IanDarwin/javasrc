package threads;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** This is the Bounce class; create and start Sprites, using Threads. */
public class Bounce extends Applet implements ActionListener {
	/** The main Panel */
	protected Panel p;
	/** The image, shared by all the Sprite objects */
	protected Image img;
	/** A Vector of Sprite objects. */
	protected Vector v;

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
		v = new Vector();
    }

    public void actionPerformed(ActionEvent e) {
		System.out.println("Creat-ing another one!");
		Sprite s = new Sprite(this, img);
		s.start();
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

