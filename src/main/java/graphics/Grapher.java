import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/** Simple Graphing program.
 * @author Ian F. Darwin, ian@darwinsys.com
 */
public class Grapher extends Component {

	/* Small inner class to hold x, y. Called Apoint to differentiate
	 * from java.awt.Point.
	 */
	class Apoint {
		float x;
		float y;
		public String toString() {
			return "Apoint("+x+","+y+")";
		}
	}

	/** The list of Apoint points. */
	protected Vector data;

	/** The minimum and maximum X values */
	protected float minx = 0, maxx = 0;
	/** The minimum and maximum Y values */
	protected float miny = 0, maxy = 0;
	/** The number of data points */
	protected int n;
	/** The range of X and Y values */
	protected float xrange, yrange;

	public Grapher() {
		data = new Vector();
	}

	/** Read the data file named. Each line has an x and a y coordinate. */
	public void read(String fname) {
		LineNumberReader is = null;
		try {
			is = new LineNumberReader(new FileReader(fname));

			String txt;
			// Read the file a line at a time, parse it, save the data.
			while ((txt = is.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(txt);
				try {
					Apoint d = new Apoint();
					d.x = Float.parseFloat(st.nextToken());
					d.y = Float.parseFloat(st.nextToken());
					data.add(d);
				} catch(NumberFormatException nfe) {
					System.err.println("Invalid number on line " +
						is.getLineNumber());
				} // XXX catch out of range exception
			}
		} catch (FileNotFoundException e) {
			System.err.println("File " + fname + " unreadable: " + e);
		} catch (IOException e) {
			System.err.println("I/O error on line " + is.getLineNumber());
		}
		n = data.size();

		// find min & max
        for (int i=0 ; i < n; i++) {
			Apoint d = (Apoint)data.elementAt(i);
			if (d.x < minx) minx = d.x;
			if (d.x > maxx) maxx = d.x;
			if (d.y < miny) miny = d.y;
			if (d.y > maxy) maxy = d.y;
        }

		// Compute ranges
		xrange = maxx - minx;
		yrange = maxy - miny;
	}

	/** Called by AWT when the window needs painting.
	 * Computes X and Y range, scales.
	 */
    public void paint(Graphics g) {
		Dimension s = getSize();
		System.out.println("Size="+s);
		if (n == 0) {
			System.out.println("... but no data!");
			return;
		}

		// Compute scale factors
		float xfact =  s.width  / xrange;
		float yfact =  s.height / yrange;

		// Scale and plot the data
        for (int i=0 ; i < n; i++) {
			Apoint d = (Apoint)data.elementAt(i);
			float x = (d.x-minx) * xfact;
			float y = d.y * yfact;
			System.out.println("AT " + i + " " + d);
			System.out.println("x = " + x + "; y = " + y);
			// AWT numbers Y from 0 down, so invert:
			g.drawRect(((int)x)-2, s.height-2-(int)y, 5, 5);
		}
    }

	public Dimension getPreferredSize() {
		return new Dimension(150, 150);
	}

	public static void main(String rgs[]) {
		final Frame f = new Frame("Grapher");
        f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		Grapher g = new Grapher();
		f.add(BorderLayout.CENTER, g);
		f.setLocation(100, 100);
		f.pack();
		g.read("Grapher.dat");
		f.setVisible(true);
	}
}
