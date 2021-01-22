package functional;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class LambdasInAction {
	static JButton b; static JTextArea c;

	public static void main(String[] args) {
		b = new JButton();
		c = new JTextArea();
		ActionListener l = 
			(ActionEvent e) -> { b.setEnabled(false);
				c.setText(e.toString());
				b.setEnabled(true); };
		System.out.println("'l' is a " + l.getClass().getName() + ")");
		b.addActionListener(l);
	}

	public class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			b.setEnabled(false);
			c.setText(e.toString());
			b.setEnabled(true);
		}
	}
}
