import java.awt.*;
import java.awt.event.*;

/** Template standalone GUI application.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class GUIextendsFrame extends Frame {
	boolean unsavedChanges = false;
	Button quitButton;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a GUIextendsFrame object, tell it to show up
		new GUIextendsFrame().setVisible(true);
	}

	/** Construct the object including its GUI */
	public GUIextendsFrame() {
		super("GUIextendsFrame");
		setLayout(new FlowLayout());
		add(new Label("Hello, and welcome to the world of Java"));
		add(quitButton = new Button("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// addWindowListener(new WindowAdapter() {
		// 	public void windowClosing(WindowEvent e) {
		// 		setVisible(false);
		// 		dispose();
		// 		System.exit(0);
		// 	}
		// });
			
		pack();
	}
}
