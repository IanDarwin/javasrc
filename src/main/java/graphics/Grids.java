import java.awt.*;
import java.awt.event.*;

/**
 * Program to draw grids.
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
class GridsCanvas extends Canvas {
	int width, height;
	int rows;
	int cols;

	GridsCanvas(int w, int h, int r, int c) {
		setSize(width=w, height=h);
		rows = r;
		cols = c;
	}

	public void paint(Graphics g) {
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

/** This is the demo class. */
public class Grids extends Frame {
	/* Construct a GfxDemo2 given its title, width and height.
	 * Uses a GridBagLayout to make the Canvas resize properly.
	 */
	Grids(String title, int w, int h, int rows, int cols) {
		setTitle(title);

		// Now create a Canvas and add it to the Frame.
		GridsCanvas xyz = new GridsCanvas(w, h, rows, cols);
		add(xyz);

        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		// Normal end ... pack it up!
		pack();
	}

	public static void main(String[] a) {
		new Grids("Test", 300, 300, 5, 10).setVisible(true);
	}
}
