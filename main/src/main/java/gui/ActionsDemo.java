package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/** An Action encapsulates:
 * textual description for button/menuitem
 * icon for display in menu/button
 * enabled/disabled state
 * an ActionListener
 */
@SuppressWarnings("serial")
public class ActionsDemo extends JPanel {
	final Action saver;

	public ActionsDemo() {
		saver = new AbstractAction("Save") {
		     public void actionPerformed(ActionEvent e) {
		       save();
		       setEnabled(false); // grays out both button and menu item
		    }
		};
		this.add(new JButton(saver));
		JMenu saveMenu = new JMenu("File");
		saveMenu.add(new JMenuItem(saver));
		// ...

	}

	protected void save() {
		System.out.println("This would save the file");
	}

	protected void editChange() {
		// make some changes to the data model...
		// ...
		saver.setEnabled(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame("ActionsDemo");
		jf.add(new ActionsDemo());
		jf.pack();
		jf.setVisible(true);
	}

}
