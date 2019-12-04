package graphics;

import java.awt.*;
import javax.swing.*;

/** FontDemo lists the system fonts and provides a sample of each one */
public class FontDemoLabel extends JFrame {
	JPanel p;

	public FontDemoLabel() {
		super("Font Demo - Label");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();

		// get font name list
		String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getAvailableFontFamilyNames();

		// IGNORE the setLayout and North/South stuff...
		// we will discuss it in a few pages!

		cp.setLayout(new BorderLayout()); 
		cp.add(BorderLayout.NORTH, new Label("Number of Fonts = " + fontList.length,
			Label.CENTER));
		cp.add(BorderLayout.CENTER, p = new JPanel());
		p.setLayout(new GridLayout(5, 0, 5, 5));

		for (String fontName : fontList) {
			JLabel lab;

			// The crux of the matter: for each font name,
			// create a label using the name as the text,
			// AND set the font to be the named font!
			p.add(lab = new JLabel(fontName));
			lab.setFont(new Font(fontName, Font.ITALIC | Font.BOLD, 14));
		}
		pack();
	}

	public static void main(String[] av) {
		new FontDemoLabel().setVisible(true);
	}
}
