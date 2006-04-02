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
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.darwinsys.swingui.UtilGUI;

/**
 * Show how to make a "Recent Files" menu.
 * @author Ian Darwin
 */
public class RecentFileMenuDemo extends JFrame {
	public final static int MAX_RECENT_FILES = 5;
	private static final String PREFS_KEY = "recentFile";	
	/** The Menu of recent files */
	final JMenu recentFilesMenu;
	/** The List of recent files */
	private List<String> recentFileNames = new ArrayList<String>();
	
	final JFileChooser chooser = new JFileChooser();
	final Preferences prefs = Preferences.userNodeForPackage(RecentFileMenuDemo.class);

	// Constructor
	RecentFileMenuDemo(String s) {
		super("MenuDemo: " + s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JMenuBar mb;
		
		Container cp = new JPanel();
		cp.setLayout(new FlowLayout());

		setJMenuBar(mb = new JMenuBar());

		recentFilesMenu = new JMenu("Recent Items");
		
		JMenuItem mi;
		
		// The File Menu...
		final JMenu fm = new JMenu("File");
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
			fm.add(recentFilesMenu);
			loadRecentMenu();
			fm.add(mi = new JMenuItem("Close"));
			mi.addActionListener(defaultHandler);
			fm.addSeparator();
			fm.add(mi = new JMenuItem("Print"));
			mi.addActionListener(defaultHandler);
			fm.addSeparator();
			fm.add(mi = new JMenuItem("Exit"));
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}			
			});
		mb.add(fm);

		// The Options Menu...
		final JMenu om = new JMenu("Options");
		
			JCheckBoxMenuItem cb = new JCheckBoxMenuItem("AutoSave");
			cb.setState(true);
			om.add(cb);
			final JMenu opSubm = new JMenu("SubOptions");
			opSubm.add(new JMenuItem("Alpha"));
			opSubm.add(new JMenuItem("Gamma"));
			opSubm.add(new JMenuItem("Delta"));
			om.add(opSubm);
		mb.add(om);

		// The Help Menu...
		final JMenu hm = new JMenu("Help");
			hm.add(mi = new JMenuItem("About"));
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(RecentFileMenuDemo.this,
						"<html><font color='green'>Ian Darwin's 'Recent Files Menu' demo");
				}
			});
			hm.add(mi = new JMenuItem("Topics"));
			mi.addActionListener(defaultHandler);
		mb.add(hm);

		// the main window
		cp.add(new JTextArea("\tMenu Demo Window", 20, 30));
		setContentPane(cp);
		pack();
		UtilGUI.center(this);
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
	
	/**
	 * Add the given filename to the top of the recent list in Prefs and in menu.
	 */
	private void putRecentFileName(String f) {
		// Trim from back end if too long
		while (recentFileNames.size() > MAX_RECENT_FILES-1) {
			recentFileNames.remove(recentFileNames.size()-1);
		}
		// Move filename to front: Remove if present, add at front.
		if (recentFileNames.contains(f)) {
			recentFileNames.remove(f);
		}
		recentFileNames.add(0, f);

		// Now save from List into Prefs
		for (int i = 0; i < recentFileNames.size(); i++) {
			String t = recentFileNames.get(i);
			prefs.put(PREFS_KEY + i, t);
		}

		// Finally, load menu again.
		loadRecentMenu();
	}

	/**
	 * Lodd or re-load the recentMenu
	 */
	private void loadRecentMenu() {
		// Clear out both all menu items and List in memory
		for (int i = recentFilesMenu.getMenuComponentCount() - 1; i >= 0; i--) {
			recentFilesMenu.remove(0);
		}
		recentFileNames.clear();

		// Copy from Prefs into Menu
		JMenuItem mi;
		for (int i = 0; i < MAX_RECENT_FILES; i++) {
			String f = prefs.get(PREFS_KEY + i, null);
			if (f == null) {	// Stop on first missing
				break;
			}
			// Drop from list if file has been deleted.
			if (new File(f).exists()) {
				// Add to List in memory
				recentFileNames.add(f);
				// And add to Menu
				recentFilesMenu.add(mi = new JMenuItem(f));
				mi.addActionListener(recentOpener);
			}
		}
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

	/** leftover action events come here, just print them. */
	private ActionListener defaultHandler = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			JComponent comp = (JComponent)evt.getSource();
			String message = "a " + comp.getClass();
			if (comp instanceof JMenuItem) {
				message = ((JMenuItem)comp).getText();
			}
			JOptionPane.showMessageDialog(RecentFileMenuDemo.this,
					"No action written yet for " + message);
		}
	};

	/** Run the whole thing */
	public static void main(String[] arg) {
		new RecentFileMenuDemo("Testing 1 2 3...").setVisible(true);
	}
}
