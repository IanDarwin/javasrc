package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Show info about the  AWT Event Dispatch Thread
 */
public class EDTInfo extends JFrame {

	public static void main(String[] args) {
		new EDTInfo().setVisible(true);
	}

	@SuppressWarnings("serial")
	EDTInfo() {
		super("EDTInfo");
		Action x = new AbstractAction("Show") {
			public void actionPerformed(ActionEvent e) {
				Thread t = Thread.currentThread();
				System.out.printf("Thread %s, isDaemon %s%n", t, t.isDaemon());
			}
		};
		add(new JButton(x));
	}

}
