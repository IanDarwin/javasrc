import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Just a Frame
 * @version $Id$
 */
public class JFrameDemo extends JFrame {
	boolean unsavedChanges = false;
	JButton quitButton;

	/** "main program" method - construct and show */
	public static void main(String av[]) {
		// create a JFrameDemo object, tell it to show up
		new JFrameDemo().setVisible(true);
	}

	/** Construct the object including its GUI */
	public JFrameDemo() {
		super("JFrameDemo");
		getContendPane().add(quitButton = new JButton("Exit"));
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
