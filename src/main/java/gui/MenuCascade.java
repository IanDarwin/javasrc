import java.awt.*;
import java.awt.image.*;

/**
 * Demonstrate Cascading Menus
 * for students of Learning Tree Course 471/478
 *
 * @author Ian Darwin
 */
public class MenuCascade extends Frame {
	MenuBar mb;
	Menu fm, om, hm;	// File, Options, Help
	Menu opSubm;			// Options Sub-Menu

	// Constructor
	MenuCascade(String s) {
		super("MenuCascade: " + s);
		mb = new MenuBar();
		setMenuBar(mb);		// Frame implements MenuContainer

		// The File Menu...
		fm = new Menu("File");
			fm.add(new MenuItem("Open", new MenuShortcut('O')));
			fm.add(new MenuItem("Close"));
			fm.addSeparator();
			fm.add(new MenuItem("Exit"));
		mb.add(fm);

		// The Options Menu...
		om = new Menu("Options");
			opSubm = new Menu("SubOptions");
			opSubm.add(new MenuItem("Alpha"));
			opSubm.add(new MenuItem("Gamma"));
			opSubm.add(new MenuItem("Delta"));
			om.add(opSubm);
		mb.add(om);

		// The Help Menu...
		hm = new Menu("Help");
			hm.add(new MenuItem("About"));
			hm.add(new MenuItem("Topics"));
		mb.add(hm);
		setSize(200,150);
		setVisible(true);
	}

	public boolean action(Event e, Object o) {
		System.out.println("You chose " + (String)o);
		if (((String)o).equals("Exit"))
			System.exit(0);
		return true;
	}

	public static void main(String arg[]) {
		MenuCascade mb = new MenuCascade("Testing 1 2 3...");
	}
}
