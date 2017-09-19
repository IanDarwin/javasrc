package patterns.creation;

import javax.swing.JOptionPane;

public class SwingRenderer implements MessageRenderer {
	public void renderMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
