package applet;

import java.applet.Applet;
import java.awt.Button;

/** Simple demo of creating a program with a button. */
@SuppressWarnings("serial")
public class TinyApplet extends Applet {

	/** The init method is used to set up the GUI */
	public void init() {
		add(new Button("A button")); // no action yet
	}
}
