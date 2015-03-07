package i18n;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/** This is a partly-internationalized version of MenuDemo.
 * To try it out, use
 *		java MenuIntl
 *		java -Duser.language=es MenuIntl
 */
@SuppressWarnings("serial")
// BEGIN main
public class MenuIntl extends JFrame {

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create an MenuIntl object, tell it to show up
		new MenuIntl().setVisible(true);
	}

	/** Construct the object including its GUI */
	public MenuIntl() {
		super("MenuIntlTest");
		JMenuItem mi;		// used in various spots

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		JLabel lab;
		cp.add(lab = new JLabel());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		ResourceBundle b = ResourceBundle.getBundle("i18n.Widgets");

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

		JMenu fm = mkMenu(b, "file");
		// In finished code there would be a call to
		// mi.addActionListener(...) after *each* of
		// these mkMenuItem calls!
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

		JMenu vm = mkMenu(b,  "view");
		vm.add(mi = mkMenuItem(b, "view", "tree"));
		vm.add(mi = mkMenuItem(b, "view", "list"));
		vm.add(mi = mkMenuItem(b, "view", "longlist"));
		mb.add(vm);

		JMenu hm = mkMenu(b,  "help");
		hm.add(mi = mkMenuItem(b, "help", "about"));
		// mb.setHelpMenu(hm);	// needed for portability (Motif, etc.).

		// the main window
		JLabel jl = new JLabel("Menu Demo Window");
		jl.setSize(200, 150);
		cp.add(jl);
		pack();
	}

	// Copies of routines that are in darwinsys.jar,
	// just here for compilation convenience

	/** Convenience routine to make a JMenu */
	public JMenu mkMenu(ResourceBundle b, String name) {
		String menuLabel;
		try { menuLabel = b.getString(name+".label"); }
		catch (MissingResourceException e) { menuLabel=name; }
		return new JMenu(menuLabel);
	}

	/** Convenience routine to make a JMenuItem */
	public JMenuItem mkMenuItem(ResourceBundle b, String menu, String name) {
		String miLabel;
		try { miLabel = b.getString(menu + "." + name + ".label"); }
		catch (MissingResourceException e) { miLabel=name; }
		String key = null;
		try { key = b.getString(menu + "." + name + ".key"); }
		catch (MissingResourceException e) { key=null; }

		if (key == null)
			return new JMenuItem(miLabel);
		else
			return new JMenuItem(miLabel, key.charAt(0));
	}

	public String lookupWithDefault(ResourceBundle rb, String key, String dflt) {
	    try {
	        return rb.getString(key);
	    } catch (MissingResourceException e) {
	        return dflt;
	    }
	}
}
// END main
