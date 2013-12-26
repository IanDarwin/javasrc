package gui;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

// BEGIN main
public class RunOnEdt {
	public static void main(String[] args) throws Exception {
		System.out.println("RunOnEdt.main()");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					JOptionPane.showMessageDialog(null, "Hello Java");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
// END main
