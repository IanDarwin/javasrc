package plotter;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

/**
 * A Plotter subclass for drawing into an AWT Window. Reflecting back
 * to AWT gives us a "known working" plotter to test on.
 * You can also steal this as a basis for your own plotter driver.
 * @author	Ian Darwin
 */
// BEGIN main
public class PlotterAWT extends Plotter {

	private JFrame f;
	private PCanvas p;
	private Graphics g;
	private Font font;
	private FontMetrics fontMetrics;

	PlotterAWT() {
		f = new JFrame("Plotter");
		Container cp = f.getContentPane();
		p = new PCanvas(MAXX, MAXY);
		cp.add(p, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g = p.getOsGraphics();
	}

	public void drawBox(int w, int h) {
		g.drawRect(curx, cury, w, h);
		p.repaint();
	}

	public void rmoveTo(int incrx, int incry){
		moveTo(curx += incrx, cury += incry);
	}

	public void moveTo(int absx, int absy){
		if (!penUp)
			g.drawLine(curx, cury, absx, absy);
		curx = absx;
		cury = absy;
	}
	
	public void lineTo(int x, int y) {
		penDown();
		moveTo(x, y);
	}

	public void setdir(float deg){}
	void penUp(){ penUp = true; }
	void penDown(){ penUp = false; }
	void penColor(int c){
		switch(c) {
		case 0: g.setColor(Color.white); break;
		case 1: g.setColor(Color.black); break;
		case 2: g.setColor(Color.red); break;
		case 3: g.setColor(Color.green); break;
		case 4: g.setColor(Color.blue); break;
		default: g.setColor(new Color(c)); break;
		}
	}
	void setFont(String fName, int fSize) {
		font = new Font(fName, Font.BOLD, fSize);
		fontMetrics = p.getFontMetrics(font);
	}
	void drawString(String s) {
		g.drawString(s, curx, cury);
		curx += fontMetrics.stringWidth(s);
	}

	/** A Member Class that contains an off-screen Image that is
	 * drawn into; this component's paint() copies from there to
	 * the screen. This avoids having to keep a list of all the
	 * things that have been drawn.
	 */
	class PCanvas extends Canvas {
		private static final long serialVersionUID = 6827371843858633606L;
		Image offScreenImage;
		int width;
		int height;
		Graphics pg;

		PCanvas(int w, int h) {
			width = w;
			height = h;
			setBackground(Color.white);
			setForeground(Color.red);
		}

		public Graphics getOsGraphics() {
			return pg;
		}

		/** This is called by AWT after the native window peer is created,
		 * and before paint() is called for the first time, so
		 * is a good time to create images and the like.
		 */
		public void addNotify() {
			super.addNotify();
			offScreenImage = createImage(width, height);
			// assert (offScreenImage != null);
			pg = offScreenImage.getGraphics();
		}

		public void paint(Graphics pg) {
			pg.drawImage(offScreenImage, 0, 0, null);
		}
		public Dimension getPreferredSize() {
			return new Dimension(width, height);
		}
	}
}
// END main
