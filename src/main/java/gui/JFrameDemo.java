import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Just a Frame
 * @version $Id$
 */
public class FrameDemo extends JFrame {
	boolean unsavedChanges = false;
	Button quitButton;

	/** "main program" method - construct and show */
	public static void main(String av[]) {
		// create a FrameDemo object, tell it to show up
		new FrameDemo().setVisible(true);
	}

	/** Construct the object including its GUI */
	public FrameDemo() {
		super("FrameDemo");
		add(quitButton = new Button("Exit"));
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
