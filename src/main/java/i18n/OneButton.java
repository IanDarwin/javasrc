package i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Create one button, internationalizedly.
 */
@SuppressWarnings("serial")
public class OneButton extends JFrame {

	public static void main(String[] args) {
		new OneButton().setVisible(true);
	}

	public OneButton() {
		ResourceBundle rb = ResourceBundle.getBundle("Widgets");
		String label = null;
		try {
			label = rb.getString("exit.label");
		} catch (MissingResourceException e) {
			label="Exit"; // fallback
		}
		this.add(new JButton(label));
	}
}
