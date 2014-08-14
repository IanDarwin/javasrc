package gui;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class JOptionStringAndComboBox {

	public static void main(String[] args) {
		
		String[] strings = { "OK" };
		JComboBox<String> combo = new JComboBox<>();
		Object[] data = { "Choose One", combo };
		combo.addItem("One");
		combo.addItem("Two");
		int n = JOptionPane.showOptionDialog(null, data, "Title", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, strings, strings[0]);
		if (n == -1) {
			JOptionPane.showMessageDialog(null, "Farewell");
			System.exit(0);
		}
		String yourChoice = (String)combo.getSelectedItem();
		JOptionPane.showMessageDialog(null, "You chose " + yourChoice, "Thanks", JOptionPane.INFORMATION_MESSAGE);
	}
}
