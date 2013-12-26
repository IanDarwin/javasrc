package gui_awt;

import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Demonstrate use of Button */
// BEGIN main
public class ButtonDemo2c extends Applet {
	Button	b;

	public void init() {
		add(b = new Button("A button"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStatus("Thanks for pushing my first button!");
			}
		});
		add(b = new Button("Another button"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStatus("Thanks for pushing my second button!");
			}
		});
	}
}
// END main
