package gui;

// Demonstrate a listener being reused.

import java.awt.Button;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerReuse extends Frame {
	public ListenerReuse() {
		Button b = new Button("Save");
		add(b);
		MenuBar mb = new MenuBar();
		setMenuBar(mb);
		Menu fm = new Menu("File");
		mb.add(fm);
		MenuItem mi = new MenuItem("Save");
		fm.add(mi);

		// Construct the ActionListener, and keep a reference to it.
		ActionListener saver = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Saving your file...");
				// In real life we would call the doSave() method 
				// in the main class, something like this:
				// mainProg.doSave();
			}
		};
		// Register the actionListener with the Button
		b.addActionListener(saver);
		// And now register the same actionListener with the MenuItem
		mi.addActionListener(saver);
		pack();
	}

	/** Main just calls the above */
	public static void main(String[] a) {
		ListenerReuse lr = new ListenerReuse();
		lr.setVisible(true);
	}
}
