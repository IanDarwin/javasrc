package template;

import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/** Simple GUI demonstration: construct a Frame and populate it.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class GUIwithFrame {

	/** "main" method */
	public static void main(String[] av) {
		Button quitButton;

		// create a GUIwithFrame object and make it show
		final JFrame f = new JFrame();
		Container cp = f.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new Label("Hello, and welcome to the world of Java"));
		cp.add(quitButton = new Button("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cp.add(new TextArea(24,80));
		f.pack();
		f.setVisible(true);
	}
}
