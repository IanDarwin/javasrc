package gui.cardlayout;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardLayoutSimple {
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		final JFrame jf = new JFrame();
		final CardLayout mgr = new CardLayout();
		jf.setLayout(mgr);
		JPanel one = new JPanel();
		one.add(new JLabel("This is the first panel"));
		one.add(new JButton(new AbstractAction("Next") {
			public void actionPerformed(ActionEvent e) {
				mgr.next(jf.getContentPane());
			}
		}));
		jf.add(one, "First");
		JPanel two = new JPanel();
		two.add(new JLabel("This is the second panel"));
		two.add(new JButton(new AbstractAction("Previous") {
			public void actionPerformed(ActionEvent e) {
				mgr.previous(jf.getContentPane());
			}
		}));
		two.add(new JButton(new AbstractAction("Finish") {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}));
		jf.add(two, "Second");
		jf.pack();
		jf.setVisible(true);
	}
}
