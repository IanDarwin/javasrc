import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** This is a partly-internationalized version of MenuDemo.
 * To try it out, use
 *		java MenuIntl
 *		java -Duser.language=es MenuIntl
 */
public class MenuIntl extends Frame {

	/** "main program" method - construct and show */
	public static void main(String av[]) {

		// create an MenuIntl object, tell it to show up
		new MenuIntl().setVisible(true);
	}

	/** Construct the object including its GUI */
	public MenuIntl() {
		super("MenuIntlTest");
		MenuItem mi;		// used in various spots
		setLayout(new FlowLayout());
		Label lab;
		add(lab = new Label());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		MenuBar mb = new MenuBar();
		setMenuBar(mb);

		ResourceBundle b = ResourceBundle.getBundle("Menus");

		String titlebar;
		try { titlebar = b.getString("program"+".title"); }
		catch (MissingResourceException e) { titlebar="MenuIntl Demo"; }
		setTitle(titlebar);

		String message;
		try { message = b.getString("program"+".message"); }
		catch (MissingResourceException e) { 
			message="Welcome to the world of Java";
		}
		lab.setText(message);

		Menu fm = mkMenu(b, "file");
		fm.add(mi = mkMenuItem(b, "file", "open"));
		fm.add(mi = mkMenuItem(b, "file", "new"));
		fm.add(mi = mkMenuItem(b, "file", "save"));
		fm.add(mi = mkMenuItem(b, "file", "exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuIntl.this.setVisible(false);
				MenuIntl.this.dispose();
				System.exit(0);
			}
		});
		mb.add(fm);

		Menu vm = mkMenu(b,  "view");
		vm.add(mi = mkMenuItem(b, "view", "tree"));
		vm.add(mi = mkMenuItem(b, "view", "list"));
		vm.add(mi = mkMenuItem(b, "view", "longlist"));
		mb.add(vm);

		Menu hm = mkMenu(b,  "help");
		hm.add(mi = mkMenuItem(b, "help", "about"));
		mb.setHelpMenu(hm);		// needed for portability (Motif, etc.).

		//pack();
		setSize(300,100);
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
