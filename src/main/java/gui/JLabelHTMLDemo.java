package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Show a JLabel containing HTML.
 */
// BEGIN main
public class JLabelHTMLDemo extends JFrame {

	/** Construct the object including its GUI */
	public JLabelHTMLDemo() {
		super("JLabelHTMLDemo");
		Container cp = getContentPane();

		JButton component = new JButton(
			"<html>" +
			"<body bgcolor='white'>" +
			"<h1><font color='red'>Welcome</font></h1>" +
			"<p>This button will be formatted according to the usual " +
			"HTML rules for formatting of paragraphs.</p>" +
			"</body></html>");

		component.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("Thank you!");
			}
		});
		cp.add(BorderLayout.CENTER, component);

		setSize(200, 400);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new JLabelHTMLDemo().setVisible(true);
	}
}
// END main
