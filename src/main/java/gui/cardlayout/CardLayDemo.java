package cardlayout;

/* CardLayout Demo: Login Applet */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/** Switchable interface lets us call back the Applet, instead of
 * having global variables (in other languages) or passing lots of
 * extra stuff into the ImgPanel constructor
 */
interface Switchable {
	void gotoNext(String target);
}

/** ImgPanel holds an Image and optional TextField, jumps to next */
class ImgPanel extends Panel {
	/** The background Image */
	Image im;
	/* Size of the Images */
	final int WIDTH = 216;
	final int HEIGHT= 144;
	/** The container */
	Switchable applet;
	/** The label for the next panel in the series (CardLayout
	 * switches based on name string, not reference to a GUI component.
	 */
	String next;
	/** Our entry field */
	TextField fld = null;
	/** Our pushbutton */
	Button butt = null;

	/** Construct an ImagePanel */
	ImgPanel(Switchable theApplet, Image image, String theNextPanelName) {
		super();

		applet	= theApplet;
		im		= image;
		next	= theNextPanelName;

		if (theNextPanelName == null)
			return;

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints cL = new GridBagConstraints(),
			cR = new GridBagConstraints();
		cL.gridx = 0; cL.gridwidth = 1;
		cR.gridx = 1; cR.gridwidth = GridBagConstraints.REMAINDER;
		cR.weightx = 1.0; cL.weightx = 4.0;
		cR.weighty = cL.weighty = 0.8;
		cR.gridheight = cL.gridheight = GridBagConstraints.REMAINDER;
		cR.anchor = GridBagConstraints.SOUTHWEST;
		cL.anchor = GridBagConstraints.SOUTHEAST;
		setLayout(gridbag);

		// Add the textfield at the left side
		fld = new TextField(8);
		gridbag.setConstraints(fld, cL);
		add(fld);

		// Add the OK button at the right side
		butt = new Button("OK");
		gridbag.setConstraints(butt, cR);
		add(butt);

		// Give it an ActionListener to move on to the next page
		butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld.getText().length() > 0)
					applet.gotoNext(next);
			}
		});
	}

	/** Set this panel's text field to hidden echo */
	public void setNoEcho() {
		if (fld != null)
			fld.setEchoChar('*');
	}
	/* override update() to not clear, which reduces flicker */
	public void update(Graphics g) {
		paint(g);
	}

	/* Draw the background */
	public void paint(Graphics g) {
		g.drawImage(im, 0, 0, this);
	}

	/* urge the Layout Mangler to make us the same size as our background */
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
}

/** CardLayDemo -- Prototype of a Login Dialog for an Applet
 *
 * @author Ian F. Darwin
 * @version 1.0, July, 1997
 */
public class CardLayDemo extends Applet implements Switchable {
	private ImgPanel loginP, passwdP, welcomeP; 
	CardLayout cardlay;

	/** Initialize the CardLayDemo applet. */
	public void init() {
		// Main panel (managed by CardLayout) is details
		// for either Login, Passwd or Welcome! screen.

		setLayout(cardlay = new CardLayout()); 

		/* create each Panel. */
		add("login",
			loginP = new ImgPanel(this, 
				getImage(getCodeBase(), "loginLogin.gif"), "passwd"));
		add("passwd",
			passwdP = new ImgPanel(this, 
				getImage(getCodeBase(), "loginPasswd.gif"), "welcome"));
		passwdP.setNoEcho();
		add("welcome",
			welcomeP = new ImgPanel(this, 
				getImage(getCodeBase(), "loginWelcome.gif"), null));
	}

	public void start() { 
		gotoNext("login");		// gotta start somewhere
	}

	/** Switch to the next panel */
	public void gotoNext(String gotoLabel) {
		cardlay.show(this, gotoLabel);
	}
}
