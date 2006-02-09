package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

/**
 * BordLayPanel.java - BorderLayout with a Panel
 * @author	Ian Darwin, http://www.darwinsys.com/, for Learning Tree Course 478
 */
public class BordLayPanel extends Frame {
	TextField fileName;
	TextArea main;
	Label status;

	public static void main(String[] av) {
		new BordLayPanel().setVisible(true);
	}

	BordLayPanel() {
		super("BordLayPanel");
		Panel p;
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH,  p = new Panel());
			p.setLayout(new FlowLayout());
			p.add(new Label("FileName:"));
			p.add(fileName  = new TextField(40));
			p.add(new Button("Load"));
			// now you need to add an action listener to the button
		add(BorderLayout.CENTER, main = new TextArea(24,80));
		add(BorderLayout.SOUTH,  status = new Label(""));
		pack();
		
		// Don't forget to add a window listener so the quit control works
	}

	/** Simulate applet.showStatus() for Frame-based applications */
	public void showStatus(String msg) {
		if (msg == null)
			msg = "";
		status.setText(msg);
	}
}
