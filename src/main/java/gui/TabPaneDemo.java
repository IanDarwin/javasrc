package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

// BEGIN main
public class TabPaneDemo {
	protected JTabbedPane tabPane;
	public TabPaneDemo() {
		tabPane = new JTabbedPane();
		tabPane.add(new JLabel("One", JLabel.CENTER), "First");
		tabPane.add(new JLabel("Two", JLabel.CENTER), "Second");
	}

	public static void main(String[] a) {
		JFrame f = new JFrame("Tab Demo");
		f.getContentPane().add(new TabPaneDemo().tabPane);
		f.setSize(120, 100);
		f.setVisible(true);
	}
}
// END main
