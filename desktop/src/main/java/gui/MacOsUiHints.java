package gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Interactive test for "macosui" package.
 * Must set Apple properties before first
 * call to any Swing constructor.
 */
public class MacOsUiHints {

	// tag::main[]
	public static void main(String[] args) throws Exception {
		System.setProperty("apple.laf.useScreenMenuBar", "true");         // <1>
		System.setProperty("apple.awt.application.name", "macOSUiHints"); // <2>
//		System.setProperty(
//			"com.apple.mrj.application.apple.menu.about.name",            // <3>
//			"MacOsUiHints");
		final MacOsUiHints gui = new MacOsUiHints( );
		SwingUtilities.invokeAndWait(() -> gui.getFrame().setVisible(true));
	}
	// end::main[]

	JFrame jf;

	protected JFrame getFrame() {
		return jf;
	}

	public MacOsUiHints( ) {
		jf = new JFrame("MacOsUiHints");
		JButton button = new JButton("Exit");
		button.addActionListener(e -> System.exit(0));
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

		Desktop desktop = Desktop.getDesktop();

		if ("Mac OS X".equals(System.getProperty("os.name"))) {
			desktop.setAboutHandler(e ->
				JOptionPane.showMessageDialog(null, """
				About MacOSUIHints\n
				Version 0.0"""));
		}
		
		jf.setSize(300, 200);

	}
}
