package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

/** Fill a Polygon */
public class PolygonFill extends Component {

	private static final long serialVersionUID = -4331761165333343021L;
	/** The points we draw */
	private Polygon p;

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawPolygon(p);
		g.setColor(Color.gray);
		g.fillPolygon(p);
	}

	/** Construct the drawing object */
	public PolygonFill() {
		p = new Polygon();
		// make a triangle.
		p.addPoint(0,100);
		p.addPoint(200,0);
		p.addPoint(200,200);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(210, 210);
	}
}
