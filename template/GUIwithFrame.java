import java.awt.*;
import java.awt.event.*;

/** Simple GUI demonstration: construct a Frame and populate it.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class GUIwithFrame {

	/** "main" method */
	public static void main(String[] av) {
		Button quitButton;

		// create a GUIwithFrame object and make it show
		final Frame f = new Frame();
		f.setLayout(new FlowLayout());
		f.add(new Label("Hello, and welcome to the world of Java"));
		f.add(quitButton = new Button("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the Frame's windowListener to be a trivial WindowAdapter
		// subclass that just calls System.exit(). Not ideal but works here.
		// DO NOT USE THIS in any program that saves user input in memory!
		// f.addWindowListener(new WindowAdapter() {
		// 	public void windowClosing(WindowEvent e) {
		// 		System.exit(0);
		// 		return;
		// 	}
		// });
		f.add(new TextArea(24,80));
		f.pack();
		f.setVisible(true);
	}
}
