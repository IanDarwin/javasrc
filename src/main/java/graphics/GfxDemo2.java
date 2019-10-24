package graphics;

import java.awt.*;

/**
 * Fancier, Canvas-based Graphics Demo for Learning Tree Course 471/478
 * We use a subclass of Canvas to do the drawing; GfxDemo2 merely
 * creates a Frame and adds the Canvas to it!
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class GfxDemo2 extends Frame {
	/* Construct a GfxDemo2 given its title, width and height.
	 * Uses a GridBagLayout to make the GfxDemoCanvas resize properly.
	 */
	GfxDemo2(String title, int w, int h) {
		setTitle(title);

		// Start of Layout stuff (ignore until AWT GUI chapter!)
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = gbc.weighty = 1.0;

		// Now create a GfxDemoCanvas and add it to the Frame.
		GfxDemoCanvas xyz = new GfxDemoCanvas(w, h);
		gbl.setConstraints(xyz, gbc);
		add(xyz);

		// Normal end ... pack it up!
		pack();
	}
	GfxDemo2(String title) {
		this(title, 300, 300);
	}

	public static void main(String[] a) {
		new GfxDemo2("Default Size").setVisible(true);
		new GfxDemo2("Smaller", 100, 100).setVisible(true);
	}
}
