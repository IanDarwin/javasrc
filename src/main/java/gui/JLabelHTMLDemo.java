import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Show a JLabel containing HTML.
 * @version $Id$
 */
public class JLabelHTMLDemo extends JFrame {

	/** Construct the object including its GUI */
	public JLabelHTMLDemo() {
		super("Demo");
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
