package graphics;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 * Digitizer - load an Image and let you click on it.
 */
public class Digitizer extends JComponent {
	Container cp;
	ImageView iv;

	/** Construct an Digitizer viewer, given a filename. */
	public Digitizer(String fname) {

		// GUI SETUP

		cp = this;
		cp.setLayout(new GridLayout(1, 0, 10, 10));

		iv = new ImageView("foo");
		cp.add(iv);

		JList list = new JList();
		cp.add(list);

		Grapher gr = new Grapher();
		cp.add(gr);

		// EVENT SETUP
		MouseClicker mickey = new MouseClicker();
		iv.addMouseListener(mickey);
		iv.addMouseMotionListener(mickey);
	}

	public void loadImage() {
		iv.loadImage();
	}

	public static void main(String[] args) {
		System.out.println("Digitizer 0.0");
		if (args.length != 1) {
			System.err.println("Usage: Digitizer file [...]");
		} else {
			for (String arg : args) {
				JFrame jf = new JFrame("Digitizer");
				Digitizer d = new Digitizer(arg);
				jf.getContentPane().add(d);
				d.loadImage();
				jf.pack();
				jf.setVisible(true);
			}
		}
	}

	/** This class is both a MouseListener and a MouseMotionListener */
	class MouseClicker extends MouseAdapter implements MouseMotionListener {

		/** Mouse pressed (i.e., clicked and released). */
		public void mousePressed(MouseEvent evt) {
			System.out.println("[" + evt.getX() + "," + evt.getY() + "]");
		}

		/** Mouse moved with action button down.
		 *  Required by MouseMotionListener
		 */
		public void mouseDragged(MouseEvent evt)   {
		}

		/** Mouse moved with action button up.
		 *  Required by MouseMotionListener
		 */
		public void mouseMoved(MouseEvent evt) {
		}
	}
}
