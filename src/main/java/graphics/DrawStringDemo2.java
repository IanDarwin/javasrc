import java.awt.*;
import javax.swing.*;

public class DrawStringDemo2 extends JComponent {
	//-
	String message = "Hello Java";

	/** Paint is called (by AWT) when it's time to draw the text. */
	public void paint(Graphics g) {
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
}

