import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** 
 * CompTest -- Component Tester.
 * A generic main program, for testing a Component-based GUI class that 
 * has a no-argument constructor. This seemed easier than adding a trivial
 * main program to every GUI component that I ever wrote...
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class CompTest {

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a Frame, and add an instance of class named in av[0].
		final JFrame f = new JFrame("CompTest: " + av[0]);
		Container cp = f.getContentPane();
		Component c = null;
		try {
			Class cf = Class.forName(av[0]);
			Object o = cf.newInstance();
			c = (Component)o; // ClassCastException?
		} catch (Exception e) {
			System.err.println("Component under test got exception in construction or initialization");
			System.err.println(e.toString());
		}
		cp.add(BorderLayout.CENTER, c);
		JButton quitButton;
		cp.add(BorderLayout.SOUTH, quitButton = new JButton("Exit")); 
		quitButton.addActionListener(new ActionListener() {
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
}
