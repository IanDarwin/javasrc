package gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ContentPane extends JFrame {
	public ContentPane() {
		Container cp = getContentPane();
		// now add Components to "cp"...
		cp.add(new JLabel("A Really Simple Demo", JLabel.CENTER));
	}
}
