import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** Template standalone Swing GUI application.
 * @author	YOUR NAME HERE, YOUR EMAIL HERE
 * FOR CVS OR RCS ADD $ AT THE FRONT OF THE FOLLOWING; else delete me and it
 * @version Id$
 */
public class TemplateSwing extends JFrame {
	Container cp;
	boolean unsavedChanges = false;
	JButton quitButton;

	/** "main program" method - construct and show */
	public static void main(String av[]) {
		// create a TemplateSwing object, tell it to show up
		new TemplateSwing().setVisible(true);
	}

	/** Construct the object including its GUI */
	public TemplateSwing() {
		super("TemplateSwing");
		cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new Label("Hello, and welcome to the world of Java"));
		cp.add(quitButton = new JButton("Exit"));
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
