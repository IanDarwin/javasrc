import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** Display your business-card information in a Java window.
 *
 * This is a very crude first attempt. The next version should
 * use a GridBagLayout.
 */
public class BusCard extends Frame {

	TextField nameTF;
	Choice jobChoice;
	Button B1, B2, B3, B4;

	/** "main program" method - construct and show */
	public static void main(String av[]) {

		// create a BusCard object, tell it to show up
		new BusCard().setVisible(true);
	}

	/** Construct the object including its GUI */
	public BusCard() {
		super();

		setLayout(new GridLayout(0, 1));

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		MenuBar mb = new MenuBar();
		setMenuBar(mb);

		ResourceBundle b = ResourceBundle.getBundle("BusCardInfo");

		Menu aMenu;
		aMenu = mkMenu(b, "filemenu");
		mb.add(aMenu);
		MenuItem mi = mkMenuItem(b, "filemenu", "exit");
		aMenu.add(mi);
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		aMenu = mkMenu(b, "editmenu");
		mb.add(aMenu);
		aMenu = mkMenu(b, "viewmenu");
		mb.add(aMenu);
		aMenu = mkMenu(b, "optionsmenu");
		mb.add(aMenu);
		aMenu = mkMenu(b, "helpmenu");
		mb.add(aMenu);
		mb.setHelpMenu(aMenu);		// needed for portability (Motif, etc.).

		String titlebar;
		try { titlebar = b.getString("card"+".company"); }
		catch (MissingResourceException e) { titlebar="BusCard Demo"; }
		setTitle(titlebar);

		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(0, 1, 50, 10));

		nameTF = new TextField(15);
		nameTF.setFont(new Font("helvetica", Font.BOLD, 18));
		String message;
		try { message = b.getString("card"+".myname"); }
		catch (MissingResourceException e) { 
			message="Java Joe";
		}
		nameTF.setText(message);
		p1.add(nameTF);

		jobChoice = new Choice();
		jobChoice.setFont(new Font("helvetica", Font.BOLD, 14));

		// XXX These should come from the Properties file loaded into "b"!
		jobChoice.add("Java Consultant");
		jobChoice.add("UNIX (OpenBSD)");
		jobChoice.add("Internet/Firewall");
		p1.add(jobChoice);

		add(p1);

		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(2, 2, 10, 10));

		B1 = new Button();
		try { message = b.getString("button1.label"); }
		catch (MissingResourceException e) { 
			message="Button1";
		}
		B1.setLabel(message);
		p2.add(B1);

		B2 = new Button();
		try { message = b.getString("button2.label"); }
		catch (MissingResourceException e) { 
			message="Button2";
		}
		B2.setLabel(message);
		p2.add(B2);

		B3 = new Button();
		try { message = b.getString("button3.label"); }
		catch (MissingResourceException e) { 
			message="Button3";
		}
		B3.setLabel(message);
		p2.add(B3);

		B4 = new Button();
		try { message = b.getString("button4.label"); }
		catch (MissingResourceException e) { 
			message="Button4";
		}
		B4.setLabel(message);
		p2.add(B4);
		add(p2);

		pack();
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
