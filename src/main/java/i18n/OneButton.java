package i18n;

import javax.swing.*;
import java.util.*;

/**
 * Create one button, internationalizedly.
 */
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
