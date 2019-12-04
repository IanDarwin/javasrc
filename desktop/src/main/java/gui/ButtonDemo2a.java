package gui;

import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/** Demonstrate use of two buttons, using a single ActionListener,
 * being the class itself.
 */
// tag::main[]
public class ButtonDemo2a extends Panel implements ActionListener {
	Button b1, b2;

	public ButtonDemo2a() {
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

	/** Cheesy showStatus replacement for migration */
	public void showStatus(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
// end::main[]
