import com.darwinsys.util.WindowCloser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Standalone GUI program that shows paint, repaint, and update */
public class PaintMethods extends JLabel {

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		final JFrame f = new JFrame("PaintMethods demo");
		f.add("Center", new PaintMethods("Testing 1 2 3"));
		f.addWindowListener(new WindowCloser(f));
		f.pack();
		f.setVisible(true);
	}

	public PaintMethods(String s) {
		super(s);
	}

	public void paint(Graphics g) {
		System.out.println("Paint");
		super.paint(g);
	}
	public void repaint() {
		System.out.println("repaint");
		super.repaint();
	}
	public void update(Graphics g) {
		System.out.println("update");
		super.update(g);
	}
}
