import java.awt.*;

/** FontDemo lists the system fonts and provides a sample of each one */
public class FontDemo extends Frame {
	String fl[];
	Panel p;

	FontDemo() {
		super("Font Demo");

		// get font name list
		fl = (Toolkit.getDefaultToolkit()).getFontList();

		// IGNORE the setLayout and North/South stuff...
		// we will discuss it in a few pages!

		setLayout(new BorderLayout()); 
		add(BorderLayout.NORTH, new Label("Number of Fonts = " + fl.length,
			Label.CENTER));
		add(BorderLayout.CENTER, p = new Panel());
		p.setLayout(new GridLayout(5,0));

		for (int i = 0; i<fl.length; i++) {
			Label lab;

			// The crux of the matter: for each font name,
			// create a label using the name as the text,
			// AND set the font to be the named font!
			p.add(lab = new Label(fl[i]));
			lab.setFont(new Font(fl[i], Font.BOLD, 14));
		}
		pack();
	}

	public static void main(String av[]) {
		new FontDemo().setVisible(true);
	}
}
