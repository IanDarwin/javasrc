import java.awt.*;

public class DrawStringDemo2 extends Component {
	//-
	String message = "Hello Java";

	public void paint(Graphics g) {
		FontMetrics fm = getFontMetrics(getFont());
		// Get the width of the String;
		int textX = (getSize().width - fm.stringWidth(message))/2;
		if (textX<0)		// If string too long, start at 0
			textX = 0;
		// Same drill for the height
		int textY = (getSize().height - fm.getLeading())/2;
		if (textY<0)
			textY = 0;
		g.drawString(message, textX, textY);
	}
	//-

	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
}

