import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Demonstrate JOptionPane
 * @author Ian Darwin
 */
public class JOptionDemo extends JFrame {

	// Constructor
	JOptionDemo(String s) {
		super(s);

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());

		JButton b = new JButton("Give me a message");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					JOptionDemo.this,
					"This is your message: etaoin shrdlu", "Coded Message",
					JOptionPane.INFORMATION_MESSAGE);
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
		JOptionDemo x = new JOptionDemo("Testing 1 2 3...");
		x.setVisible(true);
	}
}
