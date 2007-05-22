package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Demonstrate Cascading Menus
 * for students of Learning Tree Course 471/478
 *
 * @author Ian Darwin
 */
public class MenuCascade extends JFrame {
	JMenuBar mb;
	JMenu fm, om, hm;	// File, Options, Help
	JMenu opSubm;		// Options Sub-Menu
	JMenuItem mi;

	// Constructor
	MenuCascade(String s) {
		super("MenuCascade: " + s);

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());

		mb = new JMenuBar();
		setJMenuBar(mb);

		// The File Menu...
		fm = new JMenu("File");
			fm.add(mi = new JMenuItem("Open"));
			mi.addActionListener(defaultHandler);
			fm.add(mi = new JMenuItem("Close"));
			mi.addActionListener(defaultHandler);
			fm.addSeparator();
			fm.add(mi = new JMenuItem("Exit"));
			mi.addActionListener(defaultHandler);
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		mb.add(fm);

		// The Options Menu...
		om = new JMenu("Options");
			opSubm = new JMenu("SubOptions");
			opSubm.add(mi = new JMenuItem("Alpha"));
			mi.addActionListener(defaultHandler);
			opSubm.add(mi = new JMenuItem("Gamma"));
			mi.addActionListener(defaultHandler);
			opSubm.add(mi = new JMenuItem("Delta"));
			mi.addActionListener(defaultHandler);
			om.add(opSubm);
		mb.add(om);

		// The Help Menu...
		hm = new JMenu("Help");
			hm.add(mi = new JMenuItem("About"));
			mi.addActionListener(defaultHandler);
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(
					MenuCascade.this,
					"Version 0", "Version 0",
					JOptionPane.INFORMATION_MESSAGE);
				}
			});
			hm.add(mi = new JMenuItem("Topics"));
			mi.addActionListener(defaultHandler);
		mb.add(hm);

		// the main window
		cp.add(new MyCanvas("Menu Demo Window", 200, 150));
		pack();
	}

	ActionListener defaultHandler = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("You chose " + e.getActionCommand());
		}
	};

	public static void main(String[] arg) {
		MenuCascade mb = new MenuCascade("Testing 1 2 3...");
		mb.setVisible(true);
	}
}
