package gui;

import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Demonstrate use of two buttons, using a single ActionListener 
 * made of a named inner class
 */
// BEGIN main
public class ButtonDemo2b extends Applet {
	Button b1, b2;
	ActionListener handler = new ButtonHandler();

	public void init() {
		add(b1 = new Button("A button"));
		b1.addActionListener(handler);

		add(b2 = new Button("Another button"));
		b2.addActionListener(handler);
	}

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == b1)
				showStatus("Thanks for pushing my first button!");
			else
				showStatus("Thanks for pushing my second button!");
		}
	}
}
// END main
