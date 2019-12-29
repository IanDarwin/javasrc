package threads;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
	protected ExecutorService tp = Executors.newCachedThreadPool();
	/** A Vector of Sprite objects. */
	protected List<Sprite> v = new Vector<Sprite>(); // multithreaded, use Vector;

	public static void main(String[] args) {
		JFrame jf = new JFrame("Bounce Demo");
		jf.add(new Bounce(args.length > 0 ? args[0] : null));
		jf.setSize(300, 300);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    public Bounce(String imgName) {
		setLayout(new BorderLayout());
		JButton b = new JButton("Add a Sprite");
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
		JButton stopper = new JButton("Shut down");
		stopper.addActionListener(e -> {
			stop();
			tp.shutdown();
		});
		add(stopper, BorderLayout.SOUTH);
    }

    public void stop() {
		for (Sprite s : v) {
			s.stop();
		}
		v.clear();
		try {
			tp.awaitTermination(5, TimeUnit.SECONDS);
			System.out.println("ThreadPool is shut down, ending program");
			System.exit(0);
		} catch (InterruptedException e) {
			// Empty
		}
    }
}
// end::main[]
