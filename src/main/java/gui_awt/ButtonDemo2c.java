package gui_awt;

import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/** Demonstrate use of Button */
public class ButtonDemo2c extends Panel {
	Button	b;

	public ButtonDemo2c() {
		add(b = new Button("A button"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStatus("Thanks for pushing my first button!");
			}
		});
		add(b = new Button("Another button"));
		b.addActionListener(e -> {
				showStatus("Thanks for pushing my second button!");
		});
	}

	/** Cheesy showStatus replacement for Applet migration */
	public void showStatus(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
