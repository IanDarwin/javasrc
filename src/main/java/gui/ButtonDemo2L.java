package gui;

import java.awt.*;
import javax.swing.*;

/** Demonstrate a JButton with Lambda Action Listeners */
public class ButtonDemo2L extends JFrame {

	public ButtonDemo2L() {
		super("ButtonDemo Lambda");

		JButton	b;
		add(b = new JButton("A button"));
		b.addActionListener(e -> JOptionPane.showMessageDialog(this,
			"Thanks for pushing my first button!"));

		add(b = new JButton("Another button"));
		b.addActionListener(e -> JOptionPane.showMessageDialog(this,
			"Thanks for pushing my second button!");

		pack();
		setVisible(true);
	}
}
