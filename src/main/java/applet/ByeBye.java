import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/** BadGuy demo -- exit from within an applet
 * (just to show that it has no effect).
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
		System.err.println("And now, byebye!");
		System.exit(0);
	}

}
