package gui;

import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Demonstrate use of two buttons, using a single ActionListener,
 * being the class itself.
 */
// BEGIN main
public class ButtonDemo2a extends Applet implements ActionListener {
	Button b1, b2;

	public void init() {
		add(b1 = new Button("A button"));
		b1.addActionListener(this);

		add(b2 = new Button("Another button"));
		b2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1)
			showStatus("Thanks for pushing my first button!");
		else
			showStatus("Thanks for pushing my second button!");
	}
}
// END main
