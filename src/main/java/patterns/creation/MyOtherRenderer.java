package patterns.creation;

import javax.swing.JOptionPane;

public class MyOtherRenderer implements MessageRenderer {
	public void renderMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
