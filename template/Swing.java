import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** Template standalone Swing GUI application.
 * @author	YOUR NAME HERE, YOUR EMAIL HERE
 * FOR CVS OR RCS ADD $ AT THE FRONT OF THE FOLLOWING; else delete me and it
 * @version Id$
 */
public class TemplateSwing extends JComponent {
	boolean unsavedChanges = false;
	JButton quitButton;

	/** "main program" method - construct and show */
	public static void main(String av[]) {
		// create a TemplateSwing object, tell it to show up
		final JFrame f = new JFrame("TemplateSwing");
		TemplateSwing o = new TemplateSwing();
		f.getContentPane().add(o);
		o.quitButton.addActionListener(new ActionListener() {
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
	public TemplateSwing() {
		super();
		add(new Label("Hello, and welcome to the world of Java"));
		add(quitButton = new JButton("Exit")); 
		pack();
	}

	// public Dimension getMinimumSize() {
	// 	return new Dimension(50, 50);
	// }

	// public Dimension getPreferredSize() {
	// 	return new Dimension(100, 100);
	// }
}
