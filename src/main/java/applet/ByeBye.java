package applet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** BadGuy demo -- call System.exit from within an applet, just to 
 * show that it can not have the desired effect when
 * run in a "normal" browser (it will kill the AppletViewer)).
 */

public class ByeBye extends Applet implements ActionListener {
	Button b1;

	public void init() {
		System.err.println("In byebye::init()");

		setLayout(new FlowLayout());
		setFont(new Font("Helvetica", Font.PLAIN, 14));
   
		add(b1 = new Button("Exit"));
		b1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		// User pushed the (one and only) button, so we exit.
		System.err.println("And now, bye-bye!");
		System.exit(0);	// Will probably throw a SecurityException
	}

}
