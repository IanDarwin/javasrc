import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/** Demonstrate use of Button */
public class ButtonDemo extends Applet implements ActionListener {
	Button	b1;

	public ButtonDemo() {
		add(b1 = new Button("A button"));
		b1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		showStatus("Thanks for pushing my button!");
	}
}
