import java.awt.*;
import java.awt.event.*;

/**
 * GetNumber - program to determine if a number is float or int.
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class GetNumber extends Frame {
	/** The textfield to enter */
	protected TextField textField;
	/** The value, if a double */
	protected double dvalue = 0d;
	/** The value, if int */
	protected int ivalue = 0;

	public GetNumber() {
		setLayout(new FlowLayout());
		add(new Label("Number:"));
		add(textField = new TextField(10));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String s = textField.getText();
				//+
				System.out.println("Input is " + s);
				if (s.indexOf('.') >0 ||
					s.indexOf('d') >0 || s.indexOf('e') >0)
					try {
						dvalue = Double.parseDouble(s);
						System.out.println("It's a double: " + dvalue);
						return;
					} catch (NumberFormatException e) {
						System.out.println("Invalid a double: " + s);
						return;
					}
				else // did not contain . or d or e, so try as int.
					try {
						ivalue = Integer.parseInt(s);
						System.out.println("It's an int: " + ivalue);
						return;
					} catch (NumberFormatException e2) {
						System.out.println("Not a number:" + s);
					}
				}
				//-
		});
		pack();
	}

	public static void main(String[] ap) {
		new GetNumber().setVisible(true);
	}
}
