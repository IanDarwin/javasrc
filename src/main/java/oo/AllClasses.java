package oo;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

public class AllClasses {
	/** Inner class can be used anywhere in class AllClasses */
	public class Data {
		int x;
		int y;
	}
	public void getResults() {
		JButton b = new JButton("Press me");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Data loc = new Data();
				loc.x = ((Component)evt.getSource()).getX();
				loc.x = ((Component)evt.getSource()).getY();
				System.out.println("Thanks for pressing me");
			}
		});
	}
}

/** Class contained in same file as AllClasses, but can be used
 * (with a warning) in other contexts.
 */
class AnotherClass {
	// methods and fields here...
	AnotherClass() {
		// Inner class from above can not be used here, of course
		// Data d = new Data();	// EXPECT COMPILE ERROR
	}
}
