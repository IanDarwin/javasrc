package gui;

/*
 <APPLET CODE=ButtonDemoPanel WIDTH=234 HEIGHT=123>
 </APPLET>
 */

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Panel;

/** Demonstrate two buttons with a Panel for Layout.
 * This version is longer than the one in the course notes;
 * it only differs by the inclusion of background colors.
 */
public class ButtonDemoPanel extends Applet {
	Button applyB, exitB;
	public void init() {
		setBackground(Color.cyan);		// see Graphics chapter.
		Panel p = new Panel();
		p.setBackground(Color.red);
		p.add(applyB = new Button("Apply"));
		applyB.setBackground(Color.white);
		p.add(exitB = new Button("Exit"));
		exitB.setForeground(Color.red);
		add(p);  // add (connect) "p" to "this", the Applet
	}
}
