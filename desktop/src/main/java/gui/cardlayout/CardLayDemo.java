package gui.cardlayout;

import javax.swing.*;
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

/** CardLayDemo -- Prototype of a GUI Login Dialog, using CardLayout.
 * WIP - Converting from old Java web tech to JFrame; images don't work ATM.
 * @author Ian F. Darwin
 */
public class CardLayDemo extends JPanel {
	private ImagePanel passwdP; 
	CardLayout cardlay;

	/** An ImgPanel holds an Image and optional TextField; its action handler (which is
	 * probably misplaced here, see MVC) jumps to next entry in CardLayout
	 */
	class ImagePanel extends JPanel {
		/** The background Image */
		Image im;
		/* Size of the Images */
		final int WIDTH = 216;
		final int HEIGHT= 144;
		/** The container */
		CardLayDemo container;
		/** The label for the next panel in the series (CardLayout
		 * switches based on name string, not reference to a GUI component.
		 */
		String next;
		/** Our entry field */
		TextField fld = null;
		/** Our pushbutton */
		JButton butt = null;
		
		/** Construct an ImagePanel */
		ImagePanel(CardLayDemo container, Image image, String theNextPanelName) {
			super();
			
			this.container	= container;
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
			butt = new JButton("OK");
			gridbag.setConstraints(butt, cR);
			add(butt);
			
			// Give it an ActionListener to move on to the next page
			butt.addActionListener(e -> {
					if (fld.getText().length() > 0)
						container.gotoNext(next);
			});
		}

		/** Set this panel's text field to hidden echo */
		public void setNoEcho() {
			if (fld != null)
				fld.setEchoChar('*');
		}
		
		/* Urge the Layout Mangler to make us the same size as our background */
		public Dimension getPreferredSize() {
			return new Dimension(WIDTH, HEIGHT);
		}
		
		public Dimension getMinimumSize() {
			return getPreferredSize();
		}
	}

	/** DUMMY */
	Image getImage(String imageName) {
		return null;
	}

	/** Construct the CardLayDemo. */
	public CardLayDemo() {
		// Main panel (managed by CardLayout) is details
		// for either Login, Passwd or Welcome! screen.

		setLayout(cardlay = new CardLayout()); 

		/* create each Panel. */
		add("login",
			new ImagePanel(this, 
				getImage("loginLogin.gif"), "passwd"));
		add("passwd",
			passwdP = new ImagePanel(this, 
				getImage("loginPasswd.gif"), "welcome"));
		passwdP.setNoEcho();
		add("welcome",
			new ImagePanel(this, 
				getImage("loginWelcome.gif"), null));

		gotoNext("login");		// gotta start somewhere
	}

	/** Switch to the next panel */
	public void gotoNext(String gotoLabel) {
		cardlay.show(this, gotoLabel);
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame("Login Demo");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setContentPane(new CardLayDemo());
		jf.setSize(400, 300);
		jf.setVisible(true);
	}
}
