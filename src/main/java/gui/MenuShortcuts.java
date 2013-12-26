package gui;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Demonstrate JMenu shortcuts and accelerators.
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class MenuShortcuts extends JFrame implements ActionListener {
	/** The menubar */
	JMenuBar mb;
	/** File, Options, Help */
	JMenu fm, om, hm;
	/** Options Sub-JMenu */
	JMenu opSubm;
	/** The JMenuItem for exiting. */
	JMenuItem exitItem;

	// Constructor
	MenuShortcuts(String s) {
		super("JMenuShortcuts: " + s);
		mb = new JMenuBar();
		setJMenuBar(mb);		// Frame implements JMenuContainer

		Container cp = getContentPane();
		JMenuItem mi;
		// The File JMenu...
		fm = new JMenu("File");
		fm.setMnemonic('F');
		fm.add(mi = new JMenuItem("Open", 'O'));
		mi.addActionListener(this);
		fm.add(mi = new JMenuItem("Close", 'W'));
		mi.addActionListener(this);
		fm.addSeparator();

		fm.add(mi = new JMenuItem("Print", 'P'));
		mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		mi.addActionListener(this);
		fm.addSeparator();
		fm.add(mi = new JMenuItem("Exit", 'Q'));
		exitItem = mi;			// save for action handler
		mi.addActionListener(this);
		mb.add(fm);

		// The Options JMenu...
		om = new JMenu("Options");
		om.add(new JMenuItem("Alpha"));
		om.add(new JMenuItem("Gamma"));
		om.add(new JMenuItem("Delta"));
		mb.add(om);

		// The Help JMenu...
		hm = new JMenu("Help");
		hm.add(mi = new JMenuItem("About"));
		mi.addActionListener(this);
		hm.add(mi = new JMenuItem("Topics"));
		mi.addActionListener(this);
		mb.add(hm);
		// mb.setHelpMenu(hm);	// needed for portability (Motif, etc.).

		// the main window
		cp.add(new MyLabel("Menu Demo Window", 200, 150));
		pack();
	}

	/** Handle action events. */
	public void actionPerformed(ActionEvent evt) {
		Object eventSource = evt.getSource();
		String cmd;
		if ((cmd = evt.getActionCommand()) == null)
			System.out.println("You chose a menu shortcut, from " + eventSource);
		else
			System.out.println("You chose " + cmd + " from " + eventSource);
		if (eventSource == exitItem)
			System.exit(0);
	}

	public static void main(String[] arg) {
		new MenuShortcuts("Testing 1 2 3...").setVisible(true);
	}
}
