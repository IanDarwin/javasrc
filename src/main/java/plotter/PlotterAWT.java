import java.awt.*;
import java.awt.event.*;

/**
 * A Plotter subclass for drawing into an AWT Window. Reflecting back
 * to AWT gives us a "known working" plotter to test on.
 * You can also steal this as a basis for your own plotter driver!
 * 
 * @author	Ian Darwin, ian@darwinsys.com
 */
public class PlotterAWT extends Plotter {
	Frame f;
	PCanvas p;
	Graphics g;
	Font font;
	FontMetrics fontMetrics;
	PlotterAWT() {
		super();
		f = new Frame("Plotter");
		p = new PCanvas(MAXX, MAXY);
		f.add(p);
		f.pack();
		f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// If we do setVisible and dispose, then the Close completes
				PlotterAWT.this.f.setVisible(false);
				PlotterAWT.this.f.dispose();
				System.exit(0);
			}
		});
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
		if (!penIsUp)
			g.drawLine(curx, cury, absx, absy);
		curx = absx;
		cury = absy;
	}
	public void setdir(float deg){}
	void penUp(){ penIsUp = true; }
	void penDown(){ penIsUp = false; }
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
		Image os;
		int width;
		int height;
		Graphics pg;

		PCanvas(int x, int y) {
			width = x;
			height = y;
			setBackground(Color.white);
			setForeground(Color.red);
		}

		public Graphics getOsGraphics() {
			addNotify();
			return pg;
		}

		public void addNotify() {
			super.addNotify();
			os = createImage(width, height);
			if (os == null)
				throw new IllegalArgumentException("createImage failed");
			pg = os.getGraphics();
		}

		public void paint(Graphics pg) {
			pg.drawImage(os, 0, 0, null);
		}
		public Dimension getPreferredSize() {
			return new Dimension(width, height);
		}
	}
}
