import java.awt.*;
import java.awt.event.*;

/** Simple GUI demonstration: construct a Frame and populate it */
public class TemplateGUI0 {

	/** "main" method */
	public static void main(String av[]) {
		// create a TemplateGUI0 object and make it show
		Frame f = new Frame();

		// Set the Frame's windowListener to be a trivial WindowAdapter
		// subclass that just calls System.exit(). Not ideal but works here.
		// DO NOT USE THIS in any program that saves user input in memory!
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				return;
			}
		});
		f.add(new MyGUI());
		f.pack();
		f.setVisible(true);
	}
}

/** MyGUI is the actual GUI for this program */
class MyGUI extends Panel {
	/** Construct an object of this class */
	public MyGUI() {
		setLayout(new FlowLayout());
		add(new Button("Hello, and welcome to the world of Java"));
	}
}
 
