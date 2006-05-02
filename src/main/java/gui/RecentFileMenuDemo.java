package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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

import com.darwinsys.swingui.RecentMenu;
import com.darwinsys.swingui.UtilGUI;

/**
 * Show how to make a "Recent Files" menu.
 * @author Ian Darwin
 */
public class RecentFileMenuDemo extends JFrame {

	private static final long serialVersionUID = -3314639530072327565L;
	final JFileChooser chooser = new JFileChooser();

	// Constructor
	RecentFileMenuDemo(String s) {
		super("MenuDemo: " + s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JMenuBar mb;
		
		Container cp = new JPanel();
		cp.setLayout(new FlowLayout());

		setJMenuBar(mb = new JMenuBar());

		final RecentMenu recentFilesMenu = new RecentMenu(this) {
			private static final long serialVersionUID = 12345L;

			@Override
			public void loadFile(String fileName) throws IOException {
				System.out.println("Let's pretend we are loading..." + fileName);
			}			
		};
		
		JMenuItem mi;
		
		// The File Menu...
		final JMenu fm = new JMenu("File");
			fm.add(mi = new JMenuItem("Open..."));
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					String fileName = chooseFile();
					try {
						recentFilesMenu.openFile(fileName);						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(RecentFileMenuDemo.this, "IO Error reading file: " + e);
					}
				}				
			});
			fm.add(recentFilesMenu);
			JMenuItem clearItem = new JMenuItem("Clear Recent Files");
			clearItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					recentFilesMenu.clear();
				}			
			});
			fm.add(clearItem);
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
