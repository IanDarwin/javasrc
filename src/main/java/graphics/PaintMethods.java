import java.awt.*;
import java.awt.event.*;

/** Standalone GUI program that shows paint, repaint, and update */
public class PaintMethods extends Label {

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		final Frame f = new Frame("PaintMethods demo");
		f.add("Center", new PaintMethods("Testing 1 2 3"));
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
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

