import java.applet.*;
import java.awt.*;

/** Demonstrate use of Button */
public class ButtonDemo2 extends Applet {
	Button	b1, b2;

	public void init() {
		add(b1 = new Button("A button"));
		add(b2 = new Button("Another button"));
	}

	public boolean action(Event event, Object what) {
		if (event.target == b1)
			showStatus("Thanks for pushing my first button!");
		else if (event.target == b2)
			showStatus("Thanks for pushing my second button!");
		else showStatus("Huh?");
		return true;
	}
}
