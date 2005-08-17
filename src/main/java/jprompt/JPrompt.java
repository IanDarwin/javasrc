package jprompt;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Intended to become a better replacement for the X Window System "xprompt".
 * TODO command line options
 * TODO include timeout.
 */
public class JPrompt {
	/** The handler for all buttons */
	private static final ActionListener handler =
		new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JButton src = (JButton)e.getSource();
				System.out.println(src.getText().toLowerCase());
				System.exit(0);
			}		
	};
	
	/**
	 * The main program.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("Choose");
		Container cp = f.getContentPane();
		cp.setLayout(new FlowLayout());
		JButton fvwm = new JButton("FVWM");
		fvwm.addActionListener(handler);
		cp.add(fvwm);
		JButton kde = new JButton("KDE");
		kde.addActionListener(handler);
		cp.add(kde);
		f.pack();
		f.setVisible(true);
	}
}
