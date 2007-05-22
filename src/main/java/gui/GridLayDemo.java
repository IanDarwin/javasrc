package gui;

import java.applet.Applet;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Simple GridLayout demo program. */
public class GridLayDemo extends Applet implements ActionListener {
	public void init() {
		Button b;
		setLayout(new GridLayout(2,3));
		add(b=new Button("B1"));
		b.addActionListener(this);
		add(b=new Button("B2"));
		b.addActionListener(this);
		add(b=new Button("B3"));
		b.addActionListener(this);
		add(b=new Button("C1"));
		b.addActionListener(this);
		add(b=new Button("C2"));
		b.addActionListener(this);
		add(b=new Button("C3"));
		b.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ev) {
		showStatus("You pressed " + ev.getActionCommand());
	}
}
