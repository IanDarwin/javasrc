package gui_awt;

import java.awt.CheckboxMenuItem;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Demonstrate AWT Menus and the MenuBar class/MenuContainer interface
 *
 * This version uses 1.1 action handling and MenuShortcut. The action
 * handling is incomplete; realistically, each MenuItem would have its
 * own, task-specific ActionListener; this would handle the MenuShortcuts
 * and the CheckboxMenuItems correctly.
 *
 * @author Ian Darwin
 */
public class MenuDemo extends Frame
implements ActionListener, ItemListener {
	MenuBar mb;
	/** File, Options, Help */
	Menu fm, om, hm;
	/** Options Sub-Menu */
	Menu opSubm;
	/** The MenuItem for exiting. */
	MenuItem exitItem;
	/** An option that can be on or off. */
	CheckboxMenuItem cb;

	// Constructor
	MenuDemo(String s) {
		super("MenuDemo: " + s);

		Container cp = this;
		cp.setLayout(new FlowLayout());

		mb = new MenuBar();
		setMenuBar(mb);		// Frame implements MenuContainer

		MenuItem mi;
		// The File Menu...
		fm = new Menu("File");
		fm.add(mi = new MenuItem("Open", new MenuShortcut('O')));
		mi.addActionListener(this);
		fm.add(mi = new MenuItem("Close", new MenuShortcut('W')));
		mi.addActionListener(this);
		fm.addSeparator();
		fm.add(mi = new MenuItem("Print", new MenuShortcut('P')));
		mi.addActionListener(this);
		fm.addSeparator();
		fm.add(mi = new MenuItem("Exit", new MenuShortcut('Q')));
		exitItem = mi;			// save for action handler
		mi.addActionListener(this);
		mb.add(fm);

		// The Options Menu...
		om = new Menu("Options");
		cb = new CheckboxMenuItem("AutoSave");
		cb.setState(true);
		cb.addItemListener(this);
		om.add(cb);
		opSubm = new Menu("SubOptions");
		opSubm.add(new MenuItem("Alpha"));
		opSubm.add(new MenuItem("Gamma"));
		opSubm.add(new MenuItem("Delta"));
		om.add(opSubm);
		mb.add(om);

		// The Help Menu...
		hm = new Menu("Help");
		hm.add(mi = new MenuItem("About"));
		mi.addActionListener(this);
		hm.add(mi = new MenuItem("Topics"));
		mi.addActionListener(this);
		mb.add(hm);
		mb.setHelpMenu(hm);		// needed for portability (Motif, etc.).

		// the main window
		cp.add(new Label("Menu Demo Window"));
		pack();
	}

	/** Handle action events. */
	public void actionPerformed(ActionEvent evt) {
		// System.out.println("Event " + evt);
		String cmd;
		if ((cmd = evt.getActionCommand()) == null)
			System.out.println("You chose a menu shortcut");
		else
			System.out.println("You chose " + cmd);
		Object cmp = evt.getSource();
		// System.out.println("Source " + cmp);
		if (cmp == exitItem)
			System.exit(0);
	}

	/** The CheckBoxMenuItems send a different message */
	public void itemStateChanged(ItemEvent e) {
		System.out.println("AutoSave is set " + cb.getState());
	}

	public static void main(String[] arg) {
		new MenuDemo("Testing 1 2 3...").setVisible(true);
	}
}
