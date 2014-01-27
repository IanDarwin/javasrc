package functional;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class LambdasInAction {

	public static void main(String[] args) {
		JButton b = new JButton();
		JTextArea c = null;
		ActionListener l = (ActionEvent e) -> { b.disable(); c.setText(e.toString()); b.enable(); };
		System.out.println("'l' is a " + l.getClass().getName() + ")");
		b.addActionListener(l);
		//Callable c = (PurchaseVerifier pv, List<Purchases> pl) -> pv.verify(pl);
	}

}
