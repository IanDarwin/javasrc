package i18n;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.darwinsys.swingui.I18N;

/** This is a partly-internationalized version of MenuDemo.
 * To try it out, use
 *		java MenuIntl2
 *		java -Duser.language=es MenuIntl2
 */
@SuppressWarnings("serial")
public class MenuIntl2 extends JFrame {

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create an MenuIntl object, tell it to show up
		new MenuIntl2().setVisible(true);
	}

	/** Construct the object including its GUI */
	public MenuIntl2() {
		super("MenuIntlTest");
		JMenuItem mi;		// used in various spots

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		JLabel lab;
		cp.add(lab = new JLabel());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		ResourceBundle rb = ResourceBundle.getBundle("i18n.Widgets");

		String titlebar;
		try { titlebar = rb.getString("program"+".title"); }
		catch (MissingResourceException e) { titlebar="MenuIntl2 Demo"; }
		setTitle(titlebar);

		String message;
		try { message = rb.getString("program"+".message"); }
		catch (MissingResourceException e) {
			message="Welcome to the world of Java";
		}
		lab.setText(message);

		JMenu fm = I18N.mkMenu(rb, "file");
		// In finished code there would be a call to
		// mi.addActionListener(...) after *each* of
		// these mkMenuItem calls!
		fm.add(mi = I18N.mkMenuItem(rb, "file", "open"));
		fm.add(mi = I18N.mkMenuItem(rb, "file", "new"));
		fm.add(mi = I18N.mkMenuItem(rb, "file", "save"));
		fm.add(mi = I18N.mkMenuItem(rb, "file", "exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuIntl2.this.setVisible(false);
				MenuIntl2.this.dispose();
				System.exit(0);
			}
		});
		mb.add(fm);

		JMenu vm = I18N.mkMenu(rb,  "view");
		vm.add(mi = I18N.mkMenuItem(rb, "view", "tree"));
		vm.add(mi = I18N.mkMenuItem(rb, "view", "list"));
		vm.add(mi = I18N.mkMenuItem(rb, "view", "longlist"));
		mb.add(vm);

		JMenu hm = I18N.mkMenu(rb,  "help");
		hm.add(mi = I18N.mkMenuItem(rb, "help", "about"));
		// mb.setHelpMenu(hm);	// needed for portability (Motif, etc.).

		// the main window
		JLabel myLabel = new JLabel("Menu Demo Window");
		myLabel.setSize(200, 150);
		getContentPane().add(myLabel);
		pack();
	}
}
