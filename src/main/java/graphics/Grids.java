package graphics;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Program to draw grids.
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class Grids extends JFrame {

	/** Inner class that is the drawing object */
	class GridsCanvas extends JComponent {
		int width, height;
		int rows;
		int cols;

		GridsCanvas(int w, int h, int r, int c) {
			setSize(width=w, height=h);
			rows = r;
			cols = c;
		}

		@Override
		public void paintComponent(Graphics g) {
			int i;
			width = getSize().width;
			height = getSize().height;

			// draw the rows
			int rowHt = height/(rows);
			for (i = 0; i < rows; i++)
				g.drawLine(0, i*rowHt, width, i*rowHt);

			// draw the columns
			int rowWid = width/(cols);
			for (i = 0; i < cols; i++)
				g.drawLine(i*rowWid, 0, i*rowWid, height);
		}
	}

	/* Construct a GfxDemo2 given its title, width and height.
	 * Uses a GridBagLayout to make the Canvas resize properly.
	 */
	Grids(String title, int w, int h, int rows, int cols) {
		setTitle(title);

		// Now create a Canvas and add it to the Frame.
		GridsCanvas xyz = new GridsCanvas(w, h, rows, cols);
		add(xyz);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Normal end ... pack it up!
		pack();
	}

	public static void main(String[] a) {
		new Grids("Test", 300, 300, 5, 10).setVisible(true);
	}
}
