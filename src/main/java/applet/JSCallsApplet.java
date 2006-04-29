package applet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** A simple demonstration of an Applet that is called FROM JavaScript;
 * in the accompanying HTML file, the demo method is called.
 */
public class JSCallsApplet extends Applet {

	private static final long serialVersionUID = 938971166803480229L;

	@Override
	public void init() {
		add(new Label("A test applet"));
		Button b = new Button("PressMe");
		add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showStatus("in button event");
				setColor(Color.BLUE);
			}
		}); 
		System.out.println("in init");
	}

	/** This is not a method of Applet but a custom method for this class.
	 */
	public void demo() {
		showStatus("in demo");
		setColor(Color.RED);
	}

 	@Override
	public void stop() {
		System.out.println("in stop");
	}

	private void setColor(Color nc) {
		setBackground(nc);
		repaint();
	}
}
