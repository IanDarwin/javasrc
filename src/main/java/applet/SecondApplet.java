package applet;

import java.awt.Color;
import java.awt.Graphics;

/**
 * FirstApplet is a simple applet that changes color when you click on a
 * Draw button. SecondApplet extends it to allow Parameters.
 * Depends on our ColorName class to map names to java.awt.Colors.
 */
public class SecondApplet extends FirstApplet {

	private static final long serialVersionUID = 7196171133405628779L;
	/** the number of colors */
	int nColors;
	/** The array of Colors to use */
	Color colors[];

	/** init() is an Applet method called by the browser to initialize */
	public void init() {

		super.init();			// Call FirstApplet's init() to setup the GUI.

		// Now get the colors. Since we don't want the webmaster
		// to have to specify both the list of colors and the name,
		// we first count the colors.
		for (nColors=0; ; nColors++)
			if (getParameter("color" + nColors) == null)
				break;
		// Now we know the number, make the array
		if (nColors == 0)
			throw new IllegalArgumentException("SecondApplet needs colors!");
		colors = new Color[nColors];
		for (int i=0; i<nColors; i++)
			// colors[i] = ColorName.lookup(getParameter("color" + i));
			colors[i] = new Color(i, 123, 456);
	}

	/** paint() is an AWT Component method, called when the 
	 *  component needs to be painted.
	 */
	public void paint(Graphics g) {
		/* If the drawn button has been pressed, draw something */
		if (requested) {
			int w = getSize().width, h=getSize().height;
			for (int i = 0; i<nColors; i++) {
				if (colors[i] == null)
					g.setColor(Color.white);
				else
					g.setColor(colors[i]);
				g.fillRect(i*w/nColors, 0, w/nColors, h);
			}
			g.setColor(Color.black);
			g.drawString("Welcome to Java", 50, 50);
		}
	}

	/** Return information about this applet. */
	public String getAppletInfo() {
		return "SecondApplet (Parameter Demo), Version 0\n" +
			"Copyright Ian F. Darwin";
	}

	/** Return list of allowable parameters. */
	public String[][] getParameterInfo() {
		String param_info[][] = {
			{"colorN",    "n = 0, 1, ...",    "Name or hex-tuple of color"},
		};
		return param_info;
	}
}
