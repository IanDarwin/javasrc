package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

// BEGIN main
/** Demonstrate simple use of Button */
public class ButtonDemo extends JFrame implements ActionListener {
	JButton	b1;

	public ButtonDemo() {
		setLayout(new FlowLayout());
		add(b1 = new JButton("A button"));
		b1.addActionListener(this);
		setSize(300, 200);
	}

	public void actionPerformed(ActionEvent event) {
		System.out.println("Thanks for pushing my button!");
	}
	
	public static void main(String[] unuxed) {
		new ButtonDemo().setVisible(true);
	}
}
// END main
