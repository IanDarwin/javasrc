package xcolor;

import java.awt.*;
import java.awt.event.*;

/** Standalone GUI application for XColor */
public class XColorDemo extends Frame {
	/** The Label for showStatus */
	protected Label status;
	/** The button to List All colors */
	protected Button listButton;
	/** The button to exit the program */
	protected Button quitButton;

	/* Bunch of color names selected pseudo-randomly, by running
	 * awk 'NR%15==0 {print $4, $5, $6}' /usr/openwin/lib/rgb.txt
	 * on the RGB.txt file from The X Window System. Can't use the
	 * whole list as it won't fit some screens and it runs out of
	 * memory in the standard Java configuration anyway.
	 */
	protected String[] colors = {
		"PapayaWhip",
		"mint cream",
		"DarkSlateGrey",
		"light grey",
		"medium slate blue",
		"light sky blue",
		"MediumTurquoise",
		"DarkSeaGreen",
		"medium spring green",
		"pale goldenrod",
		"indian red",
		"dark salmon",
		"hot pink",
		"violet",
		"snow1",
		"bisque4",
		"cornsilk3",
		"MistyRose2",
		"blue1",
		"DeepSkyBlue4",
		"LightSteelBlue3",
		"CadetBlue2",
		"aquamarine1",
		"PaleGreen4",
		"OliveDrab3",
		"LightYellow2",
		"DarkGoldenrod1",
		"sienna4",
		"chocolate3",
		"LightSalmon2",
		"tomato1",
		"DeepPink4",
		"PaleVioletRed3",
		"orchid2",
		"purple1",
		"grey1",
		"gray9",
		"grey16",
		"gray24",
		"grey31",
		"gray39",
		"grey46",
		"gray54",
		"grey61",
		"gray69",
		"grey76",
		"gray84",
		"grey91",
		"gray99",
	};

	/** The list of all color names known to the XColor class */
	protected String[] allColors = XColor.getColorList();

	/** Constructor the XColorDemo object */
	public XColorDemo() {
		super("XColorDemo");
		Panel p = new Panel();
		p.setLayout(new GridLayout(10,5));
		Button b;
		Color c;
		for (int i=0; i<colors.length; i++) {
			p.add(b = new Button(colors[i]));
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cName = e.getActionCommand();
					Color cc = new XColor(cName);
					showStatus(cName + " " + cc.getRed() + "," +
						cc.getGreen() + "," + cc.getBlue());
				}
			});
			b.setBackground(c = new XColor(colors[i]));

			// if intensity<(50% of 3*256), treat as dark, do text in white
			int intens = c.getRed() + c.getGreen() + c.getBlue();
			if (intens<384)
				b.setForeground(Color.white);
		}
		p.add(listButton = new Button("ListAll"));
		listButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			for (int i=0; i<allColors.length; i++)
				System.out.println(allColors[i]);
			}
		});
		p.add(quitButton = new Button("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snuff();
			}
		});
		add(BorderLayout.CENTER, p);
		add(BorderLayout.SOUTH, status = new Label("XColorDemo ready; click on a color"));
		addWindowListener(new WindowAdapter() {
			/** The WindowCloser: if get here, just go away. */
			public void windowClosing(WindowEvent e) {
				snuff();
			}
		});

		pack();
	}

	void showStatus(String s) {
		status.setText(s);
	}
 
	/** "main program" method */
	public static void main(String[] av) {
		// create a XColorDemo object
		XColorDemo j = new XColorDemo();
		// send message telling it to show up
		j.setVisible(true);
	}

	/** Internal shutdown routine */
	protected void snuff() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
}
