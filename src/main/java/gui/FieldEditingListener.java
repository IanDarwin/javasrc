package gui;

import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class FieldEditingListener implements TextListener {
	TextField tf;

	public FieldEditingListener(TextField t) {
		this.tf = t;
	}

	public void textValueChanged(TextEvent ev) {
		int caret = -1;

		String s = tf.getText();
		StringBuffer sb = new StringBuffer();
		System.out.println("Text->" + s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.digit(c, 16) >= 0)
				sb.append(c);
			else
				caret = tf.getCaretPosition() - 1;
		}
		if (caret >= 0) {
			tf.setText(sb.toString());
			tf.setCaretPosition(caret);
			Toolkit.getDefaultToolkit().beep();
		}
	}
}