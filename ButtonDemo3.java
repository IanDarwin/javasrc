import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/** Demonstrate use of Button */
public class ButtonDemo3 extends Applet {
	Button	b;

	public void init() {
		class MyActionListener implements ActionListener {
			String message = null;
			MyActionListener(String msg) {
				message = msg;
			}
			public void actionPerformed(ActionEvent e) {
				showStatus(message);
			}
		}
		add(b = new Button("A button"));
		b.addActionListener(new MyActionListener("Thanks for Button 1"));
		add(b = new Button("Another button"));
		b.addActionListener(new MyActionListener("Thanks for Button 2"));
	}
}
