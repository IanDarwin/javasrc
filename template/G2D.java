package template;

import java.awt.*;

/** Template Graphics2D object
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class G2D extends Component {

	/** Construct the drawing object */
	public G2D() {
		// setup any data here
	}

	/** Paint this component, casting "Graphics g" to a Graphics2D object */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		// g2.draw(something);
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
} 
