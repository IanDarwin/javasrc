package gui_swt;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.*;

/**
 * Demonstrate SWT Menus and MenuItems.
 * <p>
 * The action handling is incomplete; realistically, each MenuItem would have
 * its own, task-specific SelectionListener.
 * 
 */
public class MenuDemo {

	/** The MenuBar */
	Menu mb;

	/** File, Options, Help */
	Menu fm, om, hm;

	/** The MenuItem for exiting. */
	MenuItem exitItem;

	/** An option that can be on or off. */
	MenuItem cb;
	
	public static void main(String[] arg) {
		Display d = new Display();
		Shell s = new Shell(d);
		MenuDemo md = new MenuDemo(d, s, "Testing 1 2 3...");
		SWTUtil.mainEventLoop(d, s);
	}

	/** Handle all the actions in one place (not real-world!!). */
	SelectionListener handler = new SelectionListener() {
		public void widgetSelected(SelectionEvent evt) {
			System.out.println("Event " + evt);
			Object cmp = evt.getSource();
			// System.out.println("Source " + cmp);
			if (cmp == exitItem)
				System.exit(0);
		}

		public void widgetDefaultSelected(SelectionEvent arg0) {
			// Nothing to do
		}
	};

	/** Construct the GUI */
	MenuDemo(Display disp, Shell sh, String s) {

		sh = new Shell(disp);
		sh.setText("MenuDemo: " + s);

		mb = new Menu(sh, SWT.BAR);

		MenuItem mi;

		// The File Menu...
		mi = new MenuItem(mb, SWT.CASCADE);
		mi.setText("File");
		fm = new Menu(sh, SWT.DROP_DOWN);
		mi.setMenu(fm);

		mi = new MenuItem(fm, SWT.NULL);
		mi.setText("Open");
		mi.addSelectionListener(handler);

		mi = new MenuItem(fm, SWT.NULL);
		mi.setText("Close");
		mi.addSelectionListener(handler);

		exitItem = new MenuItem(fm, SWT.NULL);
		exitItem.setText("Exit");
		exitItem.addSelectionListener(handler);

		// The Options Menu...
		mi = new MenuItem(mb, SWT.CASCADE);
		mi.setText("Options");
		om = new Menu(sh, SWT.DROP_DOWN);
		mi.setMenu(om);

		cb = new MenuItem(om, SWT.CHECK);
		cb.setText("AutoSave");
		cb.addSelectionListener(handler);

		mi = new MenuItem(om, SWT.CASCADE);
		mi.setText("SubOptions");
		Menu opSubm = new Menu(sh, SWT.DROP_DOWN);
		mi.setMenu(opSubm);

		mi = new MenuItem(opSubm, SWT.NULL);
		mi.setText("Alpha");
		mi.addSelectionListener(handler);

		mi = new MenuItem(opSubm, SWT.NULL);
		mi.setText("Bravo");
		mi.addSelectionListener(handler);

		mi = new MenuItem(opSubm, SWT.NULL);
		mi.setText("Delta");
		mi.addSelectionListener(handler);

		// The Help Menu...
		mi = new MenuItem(mb, SWT.CASCADE);
		mi.setText("About");
		hm = new Menu(sh, SWT.DROP_DOWN);
		mi.setMenu(hm);

		mi = new MenuItem(hm, SWT.NULL);
		mi.setText("About");
		mi.addSelectionListener(handler);
		
		mi = new MenuItem(hm, SWT.NULL);
		mi.setText("Topics");
		mi.addSelectionListener(handler);

		sh.setMenuBar(mb);

		sh.setSize(300, 200);
		sh.open();
	}
}
