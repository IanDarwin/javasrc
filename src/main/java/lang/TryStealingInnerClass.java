package lang;

import java.awt.event.*;

// This file is to show what happens if you try to access an inner class
// created in another class. Here we try to steal ButtonDemo2.java's
// inner class named MyActionListener. You will find that you can't,
// since it has no visible constructor!

class ButtonDemo2 {
	// Create an inner class which may be named ButtonDemo2$1
	Object o = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			}
	};
}

public class TryStealingInnerClass {
	public static void main(String[] a) {
		new TryStealingInnerClass().doIt();
	}
	public void doIt() {
		Object o = new ButtonDemo2$1$();	// EXPECT COMPILE ERROR
		System.out.println(o.toString());
	}
}
