package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.darwinsys.util.Debug;

/** Simple Graphing program.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Grapher extends JPanel {

	private static final long serialVersionUID = -1813143391310613248L;

	/** Multiplier for range to allow room for a border */
	public final static double BORDERFACTOR = 1.1f;

	/** The list of Point points. */
	protected List<Point2D> data;

	/** The minimum and maximum X values */
	protected double minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE;
	/** The minimum and maximum Y values */
	protected double miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
	/** The range of X and Y values */
	protected double xrange, yrange;

	public Grapher() {
		data = new ArrayList<Point2D>();
		figure();
	}

	/** Set the list data from a list of Strings, where the
	 * x coordinate is incremented automatically, and the y coordinate
	 * is made from the String in the list.
	 */
	public void setListDataFromYStrings(List<String> newData) {
		data.clear();
		for (int i=0; i < newData.size(); i++) {
			Point2D p = new Point2D.Double();
			p.setLocation(i, java.lang.Double.parseDouble(newData.get(i)));
			data.add(p);
		}
		figure();
	}

	/** Set the list from an existing List, as from GraphReader.read() */
	public void setListData(List<Point2D> newData) {
		data = newData;
		figure();
	}

	/** Compute new data when list changes */
	private void figure() {
		// find min & max
        for (int i=0 ; i < data.size(); i++) {
			Point2D d = (Point2D)data.get(i);
			if (d.getX() < minx) minx = d.getX();
			if (d.getX() > maxx) maxx = d.getX();
			if (d.getY() < miny) miny = d.getY();
			if (d.getY() > maxy) maxy = d.getY();
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
	@Override
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
			Point2D d = (Point2D)data.get(i);
			double x = (d.getX() - minx) * xfact;
			double y = (d.getY() - miny) * yfact;
			Debug.println("point", "AT " + i + " " + d + "; " +
				"x = " + x + "; y = " + y);
			// Draw a 5-pixel rectangle centered, so -2 both x and y.
			// AWT numbers Y from 0 down, so invert:
			g.drawRect(((int)x)-2, s.height-2-(int)y, 5, 5);
		}
    }

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, 150);
	}

	public static void main(String[] args) throws IOException {
		final JFrame f = new JFrame("Grapher");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Grapher grapher = new Grapher();
		f.setContentPane(grapher);
		f.setLocation(100, 100);
		f.pack();
		List<Point2D> data = null;
		if (args.length == 0)
			data = GraphReader.read("Grapher.txt");
		else {
			String fileName = args[0];
			if ("-".equals(fileName)) {
				data = GraphReader.read(new InputStreamReader(System.in),
					"System.in");
			} else {
				data = GraphReader.read(fileName);
			}
		}
		grapher.setListData(data);
		f.setVisible(true);
	}
}
// END main
