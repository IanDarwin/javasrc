package email;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Test for AliasBean.
 */
public class AliasBeanDemo extends JFrame {

	private static final long serialVersionUID = 0L;
	JButton quitButton;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a JFrameDemo object, tell it to show up
		new AliasBeanDemo().setVisible(true);
	}

	/** Construct the object including its GUI */
	public AliasBeanDemo() {
		super("AliasBeanTest");
		Container cp = getContentPane();

		AliasBean ab = new AliasBean();
		cp.add(BorderLayout.CENTER, ab);
		ab.addAlias("ian-cvs", "ian@openbsd.org");
		ab.addAlias("ian-dos", "http://www.darwinsys.com/");

		cp.add(BorderLayout.SOUTH, quitButton = new JButton("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
	
		pack();
	}
}
