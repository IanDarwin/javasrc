package i18n;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Simple Menu and Window interface - not Internationalized.
 * @author Ian Darwin
 */
public class JiltBefore
		extends JFrame
				implements ActionListener {

	private static final long serialVersionUID = 1L;
	JMenuBar mb;
	/** File, Options, Help */
	JMenu fm, om, hm;
	/** Options Sub-Menu */
	JMenu opSubm;
	/** The JMenuItem for exiting. */
	JMenuItem exitItem;

	// Constructor
	JiltBefore(String s) {
		super("JiltBefore: " + s);

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());

		mb = new JMenuBar();
		setJMenuBar(mb);

		JMenuItem mi;
		// The File Menu...
		fm = new JMenu("File");
			fm.add(mi = new JMenuItem("Open"));
			mi.addActionListener(this);
			fm.add(mi = new JMenuItem("Close"));
			mi.addActionListener(this);
			fm.addSeparator();
			fm.add(mi = new JMenuItem("Print"));
			mi.addActionListener(this);
			fm.addSeparator();
			fm.add(mi = new JMenuItem("Exit"));
			exitItem = mi;			// save for action handler
			mi.addActionListener(this);
		mb.add(fm);

		// The Options Menu...
		om = new JMenu("Options");
			fm.add(mi = new JMenuItem("Enable"));
			opSubm = new JMenu("SubOptions");
			opSubm.add(new JMenuItem("Alpha"));
			opSubm.add(new JMenuItem("Gamma"));
			opSubm.add(new JMenuItem("Delta"));
			om.add(opSubm);
		mb.add(om);

		// The Help Menu...
		hm = new JMenu("Help");
			hm.add(mi = new JMenuItem("About"));
			mi.addActionListener(this);
			hm.add(mi = new JMenuItem("Topics"));
			mi.addActionListener(this);
		mb.add(hm);
		// mb.setHelpMenu(hm);		// needed for portability (Motif, etc.).

		// the main window
		cp.add(new JLabel("Menu Demo Window"));
		// pack();
		setSize(250, 200);
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

	public static void main(String[] arg) {
		new JiltBefore("Testing 1 2 3...").setVisible(true);
	}
}
