import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Just a Frame
 * @version $Id$
 */
public class JFrameDemo extends JFrame {
	boolean unsavedChanges = false;
	JButton quitButton;

	/** Construct the object including its GUI */
	public JFrameDemo() {
		super("JFrameDemo");
		getContentPane().add(quitButton = new JButton("Exit"));

		// These "action handlers" will be explained later in the chapter.
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
	public static void main(String[] args) {
		new JFrameDemo().setVisible(true);
	}
}
