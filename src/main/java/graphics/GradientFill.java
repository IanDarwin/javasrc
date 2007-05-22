package graphics;

import java.awt.*;

/** Fill a Polygon with a colored gradient */
public class GradientFill extends Component {

	private static final long serialVersionUID = -4716199053804218498L;
	/** The points we draw */
	Polygon p;
	/** The gradient we paint with */
	GradientPaint gp;

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.draw(p);
		gp = new GradientPaint(50.0f, 50.0f, Color.red,
			75.0f, 75.0f, Color.green, true);
		g2.setPaint(gp);
		g2.fill(p);
	}

	/** Construct the drawing object */
	public GradientFill() {
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
