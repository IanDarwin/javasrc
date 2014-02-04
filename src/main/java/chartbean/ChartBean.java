package chartbean;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Simple charting bean. This version just draws a Pie Chart.
 *
 * It doesn't even label the pie slices; that is left as a
 * (non-trivial) exercise for the reader. Please read the 
 * Technical Report "How Hard can it be to draw Pie Charts?" by Chris
 * van Wyck, Purdue/Bell Labs, 1989??, before you decide how easy
 * the work is going to be!
 */
public class ChartBean extends Component {

	private static final long serialVersionUID = -9107709755814764991L;

	/** The title to print on the chart */
	protected String title;

	/** the data to draw */
	protected ChartData[] data;

	/** degrees in a circle */
	public static final int CIRCLE = 360;

	/** a set of colors to draw the pies in */
	protected Color[] colors = {
		Color.red, 
		Color.blue,
		Color.green,
		Color.pink,
		Color.orange
	};

	/** Construct a ChartBean with a title */
	public ChartBean(String s) {
		title = s;
		setBackground(Color.white);
	}
	/** Construct a ChartBean with no title (no-arg constructor
	 * required for Beans).
	 */
	public ChartBean() {
	 	this(null);
	}

	public void setLabel(String s) {
		title = s;
	}

	public String getLabel() {
		return title;
	}
	public void setData(ChartData[] newStuff) {
		data = newStuff.clone();
		repaint();
	}

	public void paint(Graphics g) {
		Dimension sz = getSize();
		int w = sz.width, h = sz.width;

		if (title != null)
			g.drawString(title, w/10, (int)(h*.9));

		if (data == null || data.length == 0) {
			g.drawOval(0, 0, w, h);
			g.drawString("Please provide some data!", w/10, h/2);
			return;
		}

		int total = 0;
		int angle = 0;
		int rad = 0;	// "radians" (actually degrees) to draw
		int colNum = 0;

		for (int i=0; i<data.length; i++)
			total += data[i].value;
		for (int i=0; i<data.length; i++) {
			rad = (int)(CIRCLE * ((float)data[i].value / (float)total));
			// System.out.println("data: "+data[i].name+";"+data[i].value+
			//	",rad="+rad);
			g.setColor(colors[colNum++]);
			colNum%=colors.length;	// keep it in bounds
			g.fillArc(0, 0, w, h, angle, rad);
			angle += rad;
		}
	}
	
	public Dimension getMinimumSize() {
		return new Dimension(100, 120);
	}
	public Dimension getPreferredSize() {
		return new Dimension(200, 240);
	}
}
