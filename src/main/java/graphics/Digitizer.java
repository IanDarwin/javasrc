import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

/**
 * Digitizer - load an Image and let you click on it.
 */
public class Digitizer extends ImageView {

	/** Construct an ImageView viewer, given a filename. */
	public Digitizer(String s) {
		super("Digitizer: " + s);

		// MouseClicker mickey = new MouseClicker();
		// cp.addMouseListener(mickey);
		// cp.addMouseMotionListener(mickey);
	}

	public static void main(String[] arg) {
		System.out.println("Digitizer 0.0");
		if (arg.length == 0) {
			System.err.println("Usage: Digitizer file [...]");
		} else {
			for (int i=0; i<arg.length; i++) {
				Window w = new Digitizer(arg[i]);
				w.setVisible(true);
			}
		}
	}
}
