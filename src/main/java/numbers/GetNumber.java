import java.awt.*;
import java.awt.event.*;

/**
 * GetNumber - program to determine if a number is float or int.
 *
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class GetNumber extends Frame {
	/** The label to display the type of thing we're editing */
	protected Label myLabel;
	/** The textfield to enter */
	protected TextField textField;
	/** The value, if a double */
	protected double dvalue = 0d;
	/** The value, if int */
	protected int ivalue = 0;

	public GetNumber() {
		setLayout(new FlowLayout());
		add(myLabel = new Label("Number:"));
		add(textField = new TextField(10));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String s = textField.getText();
				System.out.println("Input is " + s);
				if (s.indexof('.') >0 ||
					s.indexOf('d') >0 || s.indexOf('e') >0) {
					try {
						d = Double.parseDouble(s);
						System.out.println("It's a double: " + d);
						return;
					} catch (NumberFormatException e) {
						System.out.println("Invalid a double: " + s);
						return;
					}
				else // did not contain . or d or e, so try as int.
					try {
						i = Integer.parseInt(s);
						System.out.println("It's an int: " + i);
						return;
					} catch (NumberFormatException e2) {
						System.out.println("Not a number:" + s);
					}
				}
			}
		});
		pack();
	}

	public static void main(String ap[]) {
		new GetNumber().setVisible(true);
	}
}
