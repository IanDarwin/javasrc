import java.applet.*;
import java.awt.*;

/** Demonstrate use of Button */
public class ButtonDemo1_0 extends Applet {
	Button	b1;

	public ButtonDemo1_0() {
		add(b1 = new Button("A button"));
	}

	public boolean action(Event event, Object what) {
		showStatus("Thanks for pushing my button!");
		return true;
	}
}
