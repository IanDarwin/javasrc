package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * GridImageCanvas - a rectangular grid of images
 * XXX ToDo: scale images if size doesn't fit the window.
 */
public class GridImageCanvas extends JPanel {

	private static final long serialVersionUID = 2355183857034568964L;
	/** The LayoutManager. We provide it, not the user */
	protected LayoutManager lm = null;
	/** The list of Images */
	protected List<Image> vi = new Vector<Image>();
	/** The name of each Image */
	protected List<String> vs = new Vector<String>();
	/** The Panel, to manage the grid */
	protected Panel grid;
	/** The label, for showStatus */
	protected Label status;

	/** Construct a GridImageCanvas */
	GridImageCanvas() {
		setBackground(Color.red);
		grid = new Panel();
		status = new Label("Status here");
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, grid);
		add(BorderLayout.SOUTH, status);
	}

	/** Main program to allow interactive use */
	public static void main(String[] argv) {
		System.out.println("GridImageCanvas demo starting...");
		if (argv.length == 0)
			throw new IllegalArgumentException("Usage: GridImageCanvas image...");
		final JFrame f = new JFrame("GridImageCanvas");
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridImageCanvas gic = new GridImageCanvas();
		f.add(gic);
		for (int i=0; i<argv.length; i++) {
			Image im = Toolkit.getDefaultToolkit().getImage(argv[i]);
			gic.addImage(im, argv[i]);
		}
		gic.doLayout();
		f.pack();
		f.setVisible(true);
	}

	public void addImage(Image i, String s) {
		vi.add(i);
		vs.add(s);
	}

	/** We do all the Layout setup here, then call Panel.doLayout() */
	public void doLayout() {
		// doLayout called more than once?
		if (lm != null) {
			super.doLayout();
			return;
		}
		int l = vi.size();
		if (l == 0) {
			throw new IllegalArgumentException("doLayout before addImage");
		}
		if (l < 4) {
			throw new IllegalArgumentException("doLayout with <4 images");
		}
		double d = Math.sqrt((double)l);
		int w;
		if (d%1 != 0.0){
			w = ((int)d)+1;
		} else {
			w = (int)d;
		}
		// System.out.println("N="+l+";sqrt="+d+";gridLayout("+w+","+w+");");
		grid.setLayout(lm = new GridLayout(w, w));
		for (int i=0; i<l; i++) {
			ImageCanvas ic = new ImageCanvas((Image)vi.get(i), (String)vs.get(i));
			grid.add(ic);
		}
		super.doLayout();
	}

	/** dummy add(), to ensure add is NOT called directly.
	 @exception	java.lang.IllegalArgumentException	This class does its own adding; use addImage() instead.
	 */
	public Component add(Component c) {
			throw new IllegalArgumentException("add not allowed here");
	}

	public void showStatus(String s) {
		status.setText(s);
	}

	/** Inner class ImageCanvas - helper class,
	 * used only by GraphicImageCanvas, to store
	 * an image and a String, paint the Image
	 * When the mouse is in our window, we showStatus() the String.
	 */
	class ImageCanvas extends JComponent {

		private static final long serialVersionUID = -1389720987189L;
		final int PAD = 3;
		String name;
		Image im = null;
		int w, h;

		ImageCanvas(Image i, String s) {
			name = s;
			im = i;
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(im, 0);
			try {
				mt.waitForID(0);
			} catch(InterruptedException e) {
				throw new IllegalArgumentException(
					"Unexpected InterruptedException");
			}
			if (mt.isErrorID(0)) {
				throw new IllegalArgumentException(
					"Couldn't load image " + s);
			}
			addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					showStatus(name);
				}
				public void mouseExited(MouseEvent e) {
					showStatus("");
				}
			});
			setSize(w = im.getWidth(this), h = im.getHeight(this));
			repaint();
		}

		@Override
		public Dimension getMinimumSize() {
			// System.out.println("getMinimumSize() returns ("+w+","+h+");");
			return new Dimension(w, h);
		}

		@Override
		public Dimension getPreferredSize() {
			// System.out.println("getPreferredSize() returns ("+w+","+h+");");
			return new Dimension(w+PAD, h+PAD);
		}

		@Override
		public Dimension getMaximumSize() {
			// System.out.println("getMaximumSize() returns ("+w+","+h+");");
			return new Dimension(w, h);
		}

		@Override
		public void paintComponent(Graphics g) {
			if (im == null) {
				g.setColor(Color.red);
				g.fillRect(0, 0, w, h);
			} else
				g.drawImage(im, 0, 0, this);
		}
	}
}
