import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.ImageObserver;
import javax.swing.*;
import java.text.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/** A display of text, formatted by us instead of by AWT/Swing.
 * <P>
 * This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.
 * @author Ian F. Darwin
 * @version $Id$
 */
public class MyTextLayout extends Component {

	/** The text of this line */
	protected String text;
	/** The Font */
	protected Font font;
	/** The TextLayouts corresponding to "text" */
	List layouts;

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

	public void paint(Graphics g) {
		if (text == null || text.length() == 0)
			return;
		if (layouts == null)
			getLayouts(g);

		Point pen = new Point(0, 0);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(java.awt.Color.black);	// or a property
		g2d.setFont(font);

		Iterator it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = (TextLayout) it.next();
			pen.y += (layout.getAscent());
			g2d.setFont(font);
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
			//pen.y += leading;
		}
	}

	/** Lazy evaluation of the List of TextLayout objects corresponding
	 * to this MText. Some things are approximations!
	 */
	private void getLayouts(Graphics g) {
		layouts = new ArrayList();

		Point pen = new Point(10, 20);
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
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame("Demo");
		Container cp = jf.getContentPane();
		MyTextLayout tl = new MyTextLayout();
		tl.setFont(new Font("SansSerif", Font.BOLD, 42));
		tl.setText(
			"The quick brown fox jumped over the lazy cow");
		cp.add(tl);
		jf.setSize(300, 200);
		jf.setVisible(true);
	}
}
