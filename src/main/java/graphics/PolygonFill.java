import java.awt.*;

/** Fill a Polygon */
public class PolygonFill extends Component {
	/** The points we draw */
	Polygon p;

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
	public Dimension getPreferredSize() {
		return new Dimension(210, 210);
	}
} 
