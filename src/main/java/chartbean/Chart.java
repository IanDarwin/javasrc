package chartbean;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Simple charting bean. This version just draws a Pie Chart.
 *
 * It doesn't even label the pie slices; that is left as a
 * (non-trivial) exercise for the reader. Please read the 1987
 * Technical Report "How Hard can it be to draw a Pie Chart?"
 * by D. L. Souvaine and Chris van Wyck, Rutgers Computer Science,
 * LCSR-TR-90, before you decide how easy the work is going to be!
 */
public class Chart extends Component {

	private static final long serialVersionUID = 1728717236721863271L;

	/** The title to print on the chart */
	protected String title;

	/** the data to draw */
	protected ChartData[] data;

	/** The data to draw a Demo */
	protected ChartData demoData[] = {
		new ChartData(73, "Java"),
		new ChartData(15, "Microsoft"),
		new ChartData(10, "Macintosh"),
	};

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

	/** Construct a Chart with a title */
	public Chart(String s) {
		title = s;
		setBackground(Color.white);
	}
	/** Construct a Chart with no title (no-arg constructor
	 * required for Beans).
	 */
	public Chart() {
	 	this("DarwinSys ChartBean");
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
	public void doDemo() {
		setData(demoData);
	}

	public void paint(Graphics g) {
		Dimension sz = getSize();
		int w = sz.width, h = sz.width;

		if (title != null)
			g.drawString(title, w/9, (int)(h*1.1));

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
