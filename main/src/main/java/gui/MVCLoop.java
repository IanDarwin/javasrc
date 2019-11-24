package gui;

// Investigate if you can get into a loop with a Swing-based MVC app;
// in particular, if you do setSelectedIndex on a choice, does it
// generate a secondary event?  Push the button and see!

// If it does, you must be careful to avoid event propagation loops.

// This example doesn't show it, but you can loop with other implementations.
// For example, if a changed() method updates the model's data.

import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class MVCLoop {
	public static void main(String[] a) {
		Button b;
		final JComboBox ch;
		JFrame f = new JFrame("Testing");
		Container cp = f.getContentPane();
		cp.setLayout(new FlowLayout());

		b = new Button("Set");
		ch = new JComboBox();

		cp.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cur = ch.getSelectedIndex();
				int len = ch.getItemCount();
				cur++;
				cur %= len;
				ch.setSelectedIndex(cur);
			}
		});

		ch.addItem("En/ett");
		ch.addItem("Zwei");
		ch.addItem("Tres");
		ch.addItem("Four");
		ch.addItem("Funf");
		ch.addItem("Seis");
		ch.addItem("Siete");
		ch.addItem("Octo");
		cp.add(ch);
		ch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
			}
		});

		f.pack();
		f.setVisible(true);
	}
}
