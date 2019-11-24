package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/** A JTextField with a Clear Button */
public class TextFieldWithClearButton extends JFrame {

	public TextFieldWithClearButton() {
		final JTextField tf = new JTextField(15);
		tf.setLayout(new FlowLayout(FlowLayout.RIGHT));
		final Dimension d = tf.getPreferredSize();
		d.height *= 2;
		tf.setPreferredSize(d);
		JButton b1 = new JButton("X");
		tf.add(b1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				tf.setText("");
			}
		});
		final JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("Scram"));
		p.add(tf);

		setContentPane(p);

		//setSize(200, 60);
		pack();
	}

	public static void main(String[] unuxed) {
		new TextFieldWithClearButton().setVisible(true);
	}
}
