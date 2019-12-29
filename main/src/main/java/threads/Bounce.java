package threads;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** This is the Bounce class; create and start Sprites, using Threads. */
// tag::main[]
public class Bounce extends JPanel {

	private static final long serialVersionUID = -5359162621719520213L;
	/** The main Panel */
	protected JPanel p;
	/** The image, shared by all the Sprite objects */
	protected Image img;
	/** A Thread Pool */
	protected Executor tp = Executors.newCachedThreadPool();
	/** A Vector of Sprite objects. */
	protected List<Sprite> v;

	public static void main(String[] args) {
		JFrame jf = new JFrame("Bounce Demo");
		jf.add(new Bounce(args.length > 0 ? args[0] : null));
		jf.setSize(300, 300);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    public Bounce(String imgName) {
		setLayout(new BorderLayout());
		JButton b = new JButton("Start");
		b.addActionListener(e -> {
			System.out.println("Creating another one!");
			Sprite s = new Sprite(this, img);
			tp.execute(s);
			p.add(s);
			v.add(s);
	    });
		add(b, BorderLayout.NORTH);
		add(p = new JPanel(), BorderLayout.CENTER);
		p.setLayout(null);
		if (imgName == null) imgName = "duke.gif";
		final URL resource = getClass().getResource("/" + imgName);
		if (resource == null) {
			throw new IllegalStateException("Could not load image " + imgName);
		}
		img = Toolkit.getDefaultToolkit().getImage(resource);
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

    public void stop() {
		for (int i=0; i<v.size(); i++) {
			v.get(i).stop();
		}
		v.clear();
    }
}
// end::main[]
