package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/** A display of text, formatted by us instead of by AWT/Swing.
 * Recomputes widths so it resizes correctly.
 * @author Ian F. Darwin
 */
public class TextFormat extends Component {

	private static final long serialVersionUID = 1982855955721548905L;
	/** The text of this line */
	protected String text;
	/** The Font */
	protected Font font;

	public Font getFont() {
		return font;
	}

	public void setFont(Font f) {
		font = f;
	}

	public String getText() {
		return text;
	}

	public void setText(String t) {
		text = t;
	}

	@Override
	public void paint(Graphics g) {
		if (text == null || text.length() == 0)
			return;
		List<TextLayout> layouts = getLayouts(g);

		Point pen = new Point(0, 0);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.black);
		g2d.setFont(font);

		for (TextLayout layout : layouts) {
			pen.y += (layout.getAscent());
			g2d.setFont(font);
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
	}

	/** Evaluate of the List of TextLayout objects corresponding
	 * to this MText. Some things are approximations!
	 */
	private ArrayList<TextLayout> getLayouts(Graphics g) {
		ArrayList<TextLayout> layouts = new ArrayList<TextLayout>();

		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();

		AttributedString attrStr = new AttributedString(text);
		attrStr.addAttribute(TextAttribute.FONT, font, 0, text.length());
		LineBreakMeasurer measurer = new LineBreakMeasurer(
			attrStr.getIterator(), frc);
		float wrappingWidth;

		wrappingWidth = getSize().width - 15;

		while (measurer.getPosition() < text.length()) {
			TextLayout layout = measurer.nextLayout(wrappingWidth);
			layouts.add(layout);
		}
		return layouts;
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame("Demo");
		TextFormat tl = new TextFormat();
		tl.setFont(new Font("SansSerif", Font.BOLD, 42));
		tl.setText("The quick brown fox jumped over the lazy cow");
		jf.add(tl);
		jf.setSize(300, 200);
		jf.setVisible(true);
	}
}
