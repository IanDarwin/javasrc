package graphics;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

/** Standalone GUI program that shows paint, repaint, and update */
public class PaintMethods extends JLabel {

	private static final long serialVersionUID = 5804418359484349433L;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		final JFrame f = new JFrame("PaintMethods demo");
		f.getContentPane().add("Center", new PaintMethods("Testing 1 2 3"));
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public PaintMethods(String s) {
		super(s);
	}

	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("paintComponent()");
		super.paintComponent(g);
	}

	@Override
	public void repaint() {
		System.out.println("repaint");
		super.repaint();
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("paint");
		super.paint(g);
	}


	@Override
	public void update(Graphics g) {
		System.out.println("update");
		super.update(g);
	}
}
