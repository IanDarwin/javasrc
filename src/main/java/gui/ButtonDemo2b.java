/*
 <APPLET CODE=ButtonDemo2b WIDTH=234 HEIGHT=123>
 </APPLET>
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/** Demonstrate use of Button */
public class ButtonDemo2b extends Applet {
	Button	b;

	public void init() {
		add(b = new Button("A button"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStatus("Thanks for pushing my first button!");
			}
		});
		add(b = new Button("Another button"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStatus("Thanks for pushing my second button!");
			}
		});
	}
}
