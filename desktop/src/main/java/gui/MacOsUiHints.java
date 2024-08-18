package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

// tag::main[]
/**
 * Interactive test for "macosui" package.
 * Must set Apple properties before first
 * call to any Swing constructor.
 */
public class MacOsUiHints {

	public static void main(String[] args) throws Exception {
		// OS X Tester: 
		// check that the File Edit View menu appears atop the desktop not the window
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		// OS X Tester: check that this string appears in the Application Menu.
		System.setProperty("apple.awt.application.name", "macOSUiHints");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
			"MacOsUiHints");
		final MacOsUiHints gui = new MacOsUiHints( );
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				gui.getFrame().setVisible(true);
			}
		});
	}

	JFrame jf;

	protected JFrame getFrame() {
		return jf;
	}

	public MacOsUiHints( ) {
		jf = new JFrame("MacOsUiHints");
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		jf.getContentPane( ).add(button);
		
		JMenuBar mb = new JMenuBar();
		jf.setJMenuBar(mb);
		
		JMenu fileMenu = new JMenu("File");
		mb.add(fileMenu);
		var quitMI = new JMenuItem("Quit");
		fileMenu.add(quitMI);
		quitMI.addActionListener(e -> System.exit(0));
		
		var editMenu = new JMenu("Edit");
		mb.add(editMenu);
		editMenu.add(new JMenuItem("Not implemented"));
		
		// Tester: see that Application->About produces our popup
		// Ditto for Preferences and Shutdown.
		// MacOSAppAdapter adapter =
		//   new MacOSAppAdapter(jf, abouter, prefser, printer, shutter);
		//adapter.register( );
		jf.setSize(300, 200);

	}
}
// end::main[]
