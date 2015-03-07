package numbers;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GetNumber - program to determine if a number is float or int.
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class GetNumber extends Frame {

	private static final long serialVersionUID = 1L;
	/** The input textField */
	private TextField textField;
	/** The results area */
	private TextField statusLabel;

	/** Constructor: set up the GUI */
	public GetNumber() {
		Panel p = new Panel();
		p.add(new Label("Number:"));
		p.add(textField = new TextField(10));
		add(BorderLayout.NORTH, p);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String s = textField.getText();
				statusLabel.setText(process(s).toString());
			}
		});
		add(BorderLayout.SOUTH, statusLabel = new TextField(10));
		pack();
	}

	private static Number NAN = new Double(Double.NaN);

	/* Process one String, returning it as a Number subclass
	 * Does not require the GUI.
	 */
	public static Number process(String s) {
		if (s.matches("[+-]*\\d*\\.\\d+[dDeEfF]*")) {
			try {
				double dValue = Double.parseDouble(s);
				System.out.println("It's a double: " + dValue);
				return Double.valueOf(dValue);
			} catch (NumberFormatException e) {
				System.out.println("Invalid double: " + s);
				return NAN;
			}
		} else // did not contain . d e or f, so try as int.
			try {
				int iValue = Integer.parseInt(s);
				System.out.println("It's an int: " + iValue);
				return Integer.valueOf(iValue);
			} catch (NumberFormatException e2) {
				System.out.println("Not a number: " + s);
				return NAN;
			}
	}

	public static void main(String[] ap) {
		new GetNumber().setVisible(true);
	}
}
// END main
