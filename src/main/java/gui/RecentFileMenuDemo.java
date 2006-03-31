package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Show how to make a "Recent Files" menu.
 * XXX opening from recent adds it twice...
 * @author Ian Darwin
 */
public class RecentFileMenuDemo extends JFrame implements ActionListener {
	private static final String PREFS_KEY = "recentFile";
	final JMenuBar mb;
	/** File, Options, Help */
	final JMenu fm, om, hm;
	final JMenu recentMenu = new JMenu("Recent Items");
	/** Options Sub-Menu */
	final JMenu opSubm;
	/** The MenuItem for exiting. */
	final JMenuItem exitItem;
	/** An option that can be on or off. */
	JCheckBoxMenuItem cb;
	final JFileChooser chooser = new JFileChooser();
	Preferences prefs = Preferences.userNodeForPackage(RecentFileMenuDemo.class);

	// Constructor
	RecentFileMenuDemo(String s) {
		super("MenuDemo: " + s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cp = new JPanel();
		cp.setLayout(new FlowLayout());

		setJMenuBar(mb = new JMenuBar());

		JMenuItem mi;
		
		// The File Menu...
		fm = new JMenu("File");
			fm.add(mi = new JMenuItem("Open..."));
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					String fileName = chooseFile();
					try {
						openFile(fileName);						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(RecentFileMenuDemo.this, "IO Error reading file: " + e);
					}
				}				
			});
			fm.add(recentMenu);
			loadRecentMenu();
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
			cb = new JCheckBoxMenuItem("AutoSave");
			cb.setState(true);
			om.add(cb);
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

		// the main window
		cp.add(new JTextArea("Menu Demo Window", 20, 30));
		setContentPane(cp);
		pack();
	}

	/**
	 * ActionListener that is used by all the Menuitems in the Recent Menu;
	 * just opens the file named by the MenuItem's text.
	 */
	private ActionListener recentOpener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JMenuItem mi = (JMenuItem) e.getSource();
			try {
				openFile(mi.getText());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(RecentFileMenuDemo.this, "Could not open file " + e1);
			}
		}
		
	};
	
	public final static int MAX_RECENT_FILES = 5;

	private List<String> recentFileNames = new ArrayList<String>();

	/**
	 * Add the given filename to the top of the recent list in Prefs and in menu.
	 */
	private void putRecentFileName(String f) {
		while (recentFileNames.size() > MAX_RECENT_FILES) {
			recentFileNames.remove(recentFileNames.size()-1);
		}
		if (recentFileNames.contains(f)) {
			recentFileNames.remove(f);
		}
		recentFileNames.add(0, f);
		for (int i = 0; i < recentFileNames.size(); i++) {
			String t = recentFileNames.get(i);
			System.out.println("Setting " + t + " at " + i);
			prefs.put(PREFS_KEY + i, t);
		}
		loadRecentMenu();
	}

	/**
	 * Lodd or re-load the recentMenu
	 */
	private void loadRecentMenu() {
		for (int i = 0; i < recentMenu.getMenuComponentCount(); i++) {
			recentMenu.remove(0);
		}
		recentFileNames.clear();
		JMenuItem mi;
		
		for (int i = 0; i < MAX_RECENT_FILES; i++) {
			String f = prefs.get(PREFS_KEY + i, null);
			if (f == null) {	// Stop on first missing
				break;
			}
			if (new File(f).exists()) {
				recentFileNames.add(f);
				recentMenu.add(mi = new JMenuItem(f));
				mi.addActionListener(recentOpener);
			}
		}
	}

	/**
	 * Open a file (to simulate actually loading it into the model), and update
	 * the recent files list.
	 */
	private void openFile(String fileName) throws IOException {
		new FileReader(fileName);
		putRecentFileName(fileName);
		System.out.println(fileName + " opened OK");
	}

	/**
	 * Pop up a JFileChooser and return the name if the user chooses a file, else null.
	 */
	private String chooseFile() {
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			System.out.println("You chose a file named " + file.getPath());
			return file.getAbsolutePath();
		} else {
			return null;
		}
	}


	/** Handle a few action events. */
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == exitItem)
			System.exit(0);
		System.out.println("No action written yet for " + evt.getSource());
	}

	public static void main(String[] arg) {
		new RecentFileMenuDemo("Testing 1 2 3...").setVisible(true);
	}
}
