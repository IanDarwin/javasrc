package template;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Template standalone Swing GUI application.
 * @author	Ian Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class Swing extends JComponent {
	boolean unsavedChanges = false;
	JButton quitButton;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a Swing object, tell it to show up
		final JFrame f = new JFrame("Swing");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Swing comp = new Swing();
		f.getContentPane().add(comp);
		comp.quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		f.pack();
		f.setLocation(200, 200);
		f.setVisible(true);
	}

	/** Construct the object including its GUI */
	public Swing() {
		super();
		add(new Label("Hello, and welcome to the world of Java"));
		add(quitButton = new JButton("Exit")); 
	}

	// public Dimension getMinimumSize() {
	// 	return new Dimension(50, 50);
	// }

	// public Dimension getPreferredSize() {
	// 	return new Dimension(100, 100);
	// }
}
