import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** This is EXTRACTS FROM the Menu Controller for the View */
public class MenuController extends MenuBar {
	/** The View, used only for parenting Dialogs */
	View parent;
	/** The Model which we are controlling */
	Model model;

	public MenuController(View v, Model m) {
		parent = v;
		model = m;

		MenuItem mi;

		ResourceBundle b = ResourceBundle.getBundle("JabberPointMenus");
		String menuLabel;
		try { menuLabel = b.getString("file"+".label"); }
		catch (MissingResourceException e) { menuLabel="file"; }

		Menu fm = new Menu(menuLabel);
		fm.add(mi = mkMenuItem(b, "file", "open"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.loadFile(null);
			}
		});
		fm.add(mi = mkMenuItem(b, "file", "new"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.newFile();
			}
		});
		fm.add(mi = mkMenuItem(b, "file", "save"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.saveFile(null);
			}
		});
		fm.addSeparator();
		fm.add(mi = mkMenuItem(b, "file", "exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.exit(0);
			}
		});
		add(fm);

		Menu vm = mkMenu(b,  "view");
		vm.add(mi = mkMenuItem(b, "view", "next"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.nextPage();
			}
		});
		vm.add(mi = mkMenuItem(b, "view", "previous"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.prevPage();
			}
		});
		vm.addSeparator();
		vm.add(mi = mkMenuItem(b, "view", "slideshow"));
		vm.add(mi = mkMenuItem(b, "view", "outline"));
		vm.add(mi = mkMenuItem(b, "view", "slidesorter"));
		add(vm);

		Menu hm = mkMenu(b,  "help");
		hm.add(mi = mkMenuItem(b, "help", "about"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InfoDialog((Frame)parent, "About JabberPoint",
		"JabberPoint(tm) -- the free, cross-platform slidwshow\n " +
		"JabberPoint is a primitive slide-show program in Java(tm). It\n" +
		"is freely copyable as long as you keep this notice and\n" +
		"the splash screen intact.\n" +
		"Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.\n" +
		"Author's version available from http://www.darwinsys.com/").setVisible(true);
			}
		});
		setHelpMenu(hm);		// needed for portability (Motif, etc.).

	}

	/** Convenience routine to make a Menu */
	public Menu mkMenu(ResourceBundle b, String name) {
		String menuLabel;
		try { menuLabel = b.getString(name+".label"); }
		catch (MissingResourceException e) { menuLabel=name; }
		return new Menu(menuLabel);
	}

	/** Convenience routine to make a MenuItem */
	public MenuItem mkMenuItem(ResourceBundle b, String menu, String name) {
		String miLabel;
		try { miLabel = b.getString(menu + "." + name + ".label"); }
		catch (MissingResourceException e) { miLabel=name; }
		String key = null;
		try { key = b.getString(menu + "." + name + ".key"); }
		catch (MissingResourceException e) { key=null; }

		if (key == null)
			return new MenuItem(miLabel);
		else
			return new MenuItem(miLabel, new MenuShortcut(key.charAt(0)));
	}

}
