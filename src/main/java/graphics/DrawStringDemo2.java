package graphics;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

/** Demo showing code that draws text centered in a JComponent.
 * In real life you'd just use a JLabel; this is to show the
 * mechanics of doing such a thing.
 */
// BEGIN main
public class DrawStringDemo2 extends JComponent {

	private static final long serialVersionUID = -6593901790809089107L;
	//-
	String message = "Hello Java";

	/** Called by the window system to draw the text. */
	@Override
	public void paintComponent(Graphics g) {

		// Get the current Font, and ask it for its FontMetrics.
		FontMetrics fm = getFontMetrics(getFont());

		// Use the FontMetrics to get the width of the String.
		// Subtract this from width, divide by 2, that's our starting point.
		int textX = (getSize().width - fm.stringWidth(message))/2;
		if (textX<0)		// If string too long, start at 0
			textX = 0;

		// Same as above but for the height
		int textY = (getSize().height - fm.getAscent())/2 - fm.getDescent();
		if (textY < 0)
			textY = getSize().height - fm.getDescent() - 1;

		// Now draw the text at the computed spot.
		g.drawString(message, textX, textY);
	}
	//-

	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}

	public static void main(final String[] args) {
		final JFrame jf = new JFrame();
		jf.add(new DrawStringDemo2());
		jf.setBounds(100, 100, 100, 100);
		jf.setVisible(true);
	}
}

// END main
