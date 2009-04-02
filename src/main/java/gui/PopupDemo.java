package gui;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/** Provide a pop-up menu using a Frame.
 * On most platforms, changing the mouse "settings" changes how the
 * isPopupTrigger() method behaves instantly - which is as it should be!
 */
public class PopupDemo extends Frame {

	/** "main" demo method . */
	public static void main(String[] av) {
		new PopupDemo("Hello").setVisible(true);
	}

	/** Construct the main program */
	public PopupDemo(String title) {
		super(title);

		setLayout(new FlowLayout());
		add(new PopupContainer(
			"Hello, and welcome to the world of Java"));
		pack();
		setVisible(true);
	}
	
	/* A component to demonstrate use of PopupMenu.
	 * The user has to ask for the menu to popup in Java's
	 * platform-dependant way (e.g., right mouse click on X Windows, MS-Windows).
	 *
	 * Alternately, you could watch for keypress events and provide
	 * your own platform-independant keyboard popup menu character
	 * such as M for Menu (not CTRL/M; Mac's don't have a CTRL key).
	 */
	class PopupContainer extends Component {
		PopupMenu m;
		PopupContainer(String s) {
			m = new PopupMenu(s);
			m.add(new MenuItem("Open"));
			m.add(new MenuItem("Close"));
			MenuItem qB;
			m.add(qB = new MenuItem("Exit"));
			class Quitter implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			}
			qB.addActionListener(new Quitter());
			
			add(m);		// add Popup to Component
			
			enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		}
		
		/**
		 * This overrides processMouseEvent on the Container; better would
		 * be to add a MouseListener (or MouseAdapter) to do this work.
		 */
		public void processMouseEvent(MouseEvent me) {
			System.err.println("MouseEvent: " + me);
			if (me.isPopupTrigger())
				m.show(this, me.getX(), me.getY());
			else
				super.processMouseEvent(me);
		};
		
		/** Compute our minimum size */
		public Dimension getMinimumSize() {
			return new Dimension(200, 200);
		}
		
		final int PREF_PAD=10;
		
		/** Computer our best size */
		public Dimension getPreferredSize() {
			Dimension d = getMinimumSize();
			return new Dimension(d.width+PREF_PAD, d.height+PREF_PAD);
		}
		
		/** Compute our maximum allowed size */
		public Dimension getMaximumSize() {
			Dimension d = getMinimumSize();
			return new Dimension(d.width*2, d.height*2);
		}
	}
}
