package template;

import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/** Template standalone GUI application.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class GUIextendsFrame extends JFrame {
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
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new Label("Hello, and welcome to the world of Java"));
		cp.add(quitButton = new Button("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		pack();
	}
}
