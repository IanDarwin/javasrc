package packaging;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;

/**
 * HelloApplet is a simple applet that toggles a message
 * when you click on a Draw button.
 */
// BEGIN main
public class HelloApplet extends JApplet {

	/** The flag which controls drawing the message. */
	protected boolean requested;

	/** init() is an Applet method called by the browser to initialize */
	public void init() {
		JButton b;
		requested = false;
		Container cp = (Container)getContentPane();
		cp.setLayout(new FlowLayout());
		String buttonLabel = getParameter("buttonlabel");
		if (buttonLabel == null) {
			buttonLabel = "Draw/Don't Draw";
		}
		cp.add(b = new JButton(buttonLabel));
		b.addActionListener(new ActionListener() {
			/*  Button - toggle the state of the "requested" flag, to draw or
			 *  not to draw.
			 */
			public void actionPerformed(ActionEvent e) {
				String arg = e.getActionCommand();
				// Invert the state of the draw request.
				requested = !requested;
				do_the_work();
			}
		});
	}

	/** paint() is an AWT Component method, called when the 
	 *  component needs to be painted.
	 */
	public void do_the_work() {
		/* If the Draw button is selected, draw something */
		if (requested) {
			showStatus("Welcome to Java!");
		} else {
			showStatus("");	// retract welcome? :-)
		}
	}
}
// END main
