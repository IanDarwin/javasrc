import java.awt.*;
import java.applet.*;

/** Simple demo of creating a program with a button. */
public class TinyApplet extends Applet {

	/** The init method is used to set up the GUI */
	public void init() {
		add(new Button("A button")); // no action yet
	}
}
