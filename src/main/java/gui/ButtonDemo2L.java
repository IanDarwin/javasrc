package gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// BEGIN main
/** Demonstrate a JButton with Lambda Action Listeners */
public class ButtonDemo2L extends JFrame {

	private static final long serialVersionUID = 1L;

	public ButtonDemo2L() {
		super("ButtonDemo Lambda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JButton	b;
		add(b = new JButton("A button"));
		// Minimalist style
		b.addActionListener(e -> JOptionPane.showMessageDialog(this,
			"Thanks for pushing my first button!"));

		add(b = new JButton("Another button"));
		// Longer style, with { } around body.
		b.addActionListener(e -> {
			JOptionPane.showMessageDialog(this,
					"Thanks for pushing my second button!");
			}
		);

		pack();
	}
	
	public static void main(String[] args) {
		new ButtonDemo2L().setVisible(true);
	}
}
// END main
