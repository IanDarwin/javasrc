import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

import com.darwinsys.util.Debug;

/** Simple Graphing program.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Grapher extends JPanel {
	/** Multiplier for range to allow room for a border */
	public final static double BORDERFACTOR = 1.1f;

	/** The list of Point points. */
	protected List data;

	/** The minimum and maximum X values */
	protected double minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE;
	/** The minimum and maximum Y values */
	protected double miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
	/** The range of X and Y values */
	protected double xrange, yrange;

	public Grapher() {
		data = new ArrayList();
		figure();
	}

	/** Set the list data from a list of Strings, where the
	 * x coordinate is incremented automatically, and the y coordinate
	 * is made from the String in the list.
	 */
	public void setListDataFromYStrings(List newData) {
		data.clear();
		for (int i=0; i < newData.size(); i++) {
			Point p = new Point();
			p.setLocation(i, Double.parseDouble((String)newData.get(i)));
			data.add(p);
		}
		figure();
	}

	/** Set the list from an existing List, as from GraphReader.read() */
	public void setListData(List newData) {
		data = newData;
		figure();
	}

	/** Compute new data when list changes */
	private void figure() {
		// find min & max
        for (int i=0 ; i < data.size(); i++) {
			Point d = (Point)data.get(i);
			if (d.x < minx) minx = d.x;
			if (d.x > maxx) maxx = d.x;
			if (d.y < miny) miny = d.y;
			if (d.y > maxy) maxy = d.y;
        }

		// Compute ranges
		xrange = (maxx - minx) * BORDERFACTOR;
		yrange = (maxy - miny) * BORDERFACTOR;
		Debug.println("range", "minx,x,r = " + minx +' '+ maxx +' '+ xrange);
		Debug.println("range", "miny,y,r = " + miny +' '+ maxy +' '+ yrange);
	}

	/** Called when the window needs painting.
	 * Computes X and Y range, scales.
	 */
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension s = getSize();
		if (data.size() < 2) {
			g.drawString("Insufficient data: " + data.size(), 10, 40);
			return;
		}

		// Compute scale factors
		double xfact =  s.width  / xrange;
		double yfact =  s.height / yrange;

		// Scale and plot the data
        for (int i=0 ; i < data.size(); i++) {
			Point d = (Point)data.get(i);
			double x = (d.x-minx) * xfact;
			double y = (d.y-miny) * yfact;
			Debug.println("point", "AT " + i + " " + d + "; " +
				"x = " + x + "; y = " + y);
			// Draw a 5-pixel rectangle centered, so -2 both x and y.
			// AWT numbers Y from 0 down, so invert:
			g.drawRect(((int)x)-2, s.height-2-(int)y, 5, 5);
		}
    }

	public Dimension getPreferredSize() {
		return new Dimension(150, 150);
	}

	public static void main(String[] args) {
		final JFrame f = new JFrame("Grapher");
        f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		Grapher g = new Grapher();
		f.setContentPane(g);
		f.setLocation(100, 100);
		f.pack();
		if (args.length == 0)
			g.setListData(GraphReader.read("Grapher.dat"));
		else
			g.setListData(GraphReader.read(args[0]));
		f.setVisible(true);
	}
}
