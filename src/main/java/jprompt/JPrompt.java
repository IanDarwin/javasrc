package jprompt;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Intended to become a better replacement for the X Window System "xprompt".
 * TODO include timeout thread.
 */
public class JPrompt extends JFrame {
	/** The handler for all buttons */
	private final ActionListener handler =
		new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JButton src = (JButton)e.getSource();
				System.out.println(src.getText().toLowerCase());
				System.exit(0);
			}		
	};
	
	/**
	 * Constructor for JPrompt, called after main() has processed
	 * and removed from args any options.
	 * @param args Just the list of button labels to appear.
	 */
	public JPrompt(String[] args) {
		super("Choose");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		boolean first = true;
		for (String label : args) {
			JButton button = new JButton(label);
			button.addActionListener(handler);
			cp.add(button);
			if (first) {
				button.requestFocus(); 
				first = false;
			}
		}
		
		pack();
		
		centre(this);
	}
	
	/**
	 * The main program.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO process command line arguments
		// Ideas: -b ButtonText -t TrueFalseText [-r reply ]
		new JPrompt(args).setVisible(true);
	}
	
	/** Centre a Window, Frame, JFrame, Dialog, etc. */
	public static void centre(Window w) {
		// After packing a Frame or Dialog, centre it on the screen.
		Dimension us = w.getSize(), 
			them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height- us.height)/ 2;
		w.setLocation(newX, newY);
	}

}
