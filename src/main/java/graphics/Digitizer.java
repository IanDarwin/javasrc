import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

/**
 * Digitizer - load an Image and let you click on it.
 * STAUTS - NOT WORKING!!
 */
public class Digitizer extends JComponent {
	Container cp;

	/** Construct an Digitizer viewer, given a filename. */
	public Digitizer(String fname) {

		// GUI SETUP

		cp = this;
		cp.setLayout(new GridLayout(1, 0, 10, 10));

		ImageView iv = new ImageView("Digitizer: " + fname);
		iv.loadImage();
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

	public static void main(String[] arg) {
		System.out.println("Digitizer 0.0");
		if (arg.length != 1) {
			System.err.println("Usage: Digitizer file [...]");
		} else {
			for (int i=0; i<arg.length; i++) {
				JFrame jf = new JFrame("Digitizer");
				jf.getContentPane().add(new Digitizer(arg[i]));
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
