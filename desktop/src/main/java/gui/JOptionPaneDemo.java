package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Demonstrate JOptionPane
 * @author Ian Darwin
 */
@SuppressWarnings("serial")
public class JOptionPaneDemo extends JFrame {

	// Constructor
	JOptionPaneDemo(String s) {
		super(s);

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());

		JButton b = new JButton("Informational");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					JOptionPaneDemo.this,
					"This is your information: etaoin shrdlu", "Coded Message",
					JOptionPane.INFORMATION_MESSAGE);
			}
		});
		cp.add(b);

		b = new JButton("Warning");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					JOptionPaneDemo.this,
					"This is your warning: etaoin shrdlu", "Coded Message",
					JOptionPane.WARNING_MESSAGE);
			}
		});
		cp.add(b);

		b = new JButton("Error");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					JOptionPaneDemo.this,
					"This is your error message: etaoin shrdlu", "Coded Message",
					JOptionPane.ERROR_MESSAGE);
			}
		});
		cp.add(b);

		b = new JButton("Goodbye!");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cp.add(b);

		// size the main window
		pack();
	}

	public static void main(String[] arg) {
		JOptionPaneDemo x = new JOptionPaneDemo("Testing 1 2 3...");
		x.setVisible(true);
	}
}
