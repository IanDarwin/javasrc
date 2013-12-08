package gui.cardlayout;

import java.applet.Applet;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Switchable interface lets us call back the Applet, instead of
 * having global variables (in other languages) or passing lots of
 * extra stuff into the ImgPanel constructor
 */
interface Switchable {
	void gotoNext(String target);
}

/** CardLayDemo -- Prototype of a GUI Login Dialog, using CardLayout.
 * @author Ian F. Darwin
 */
public class CardLayDemo extends Applet implements Switchable {
	private ImagePanel passwdP; 
	CardLayout cardlay;


	/** An ImgPanel holds an Image and optional TextField; its action handler (which is
	 * probably misplaced here, see MVC) jumps to next entry in CardLayout
	 */
	class ImagePanel extends Panel {
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
		ImagePanel(Switchable theApplet, Image image, String theNextPanelName) {
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

	/** Initialize the CardLayDemo applet. */
	public void init() {
		// Main panel (managed by CardLayout) is details
		// for either Login, Passwd or Welcome! screen.

		setLayout(cardlay = new CardLayout()); 

		/* create each Panel. */
		add("login",
			new ImagePanel(this, 
				getImage(getCodeBase(), "loginLogin.gif"), "passwd"));
		add("passwd",
			passwdP = new ImagePanel(this, 
				getImage(getCodeBase(), "loginPasswd.gif"), "welcome"));
		passwdP.setNoEcho();
		add("welcome",
			new ImagePanel(this, 
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
