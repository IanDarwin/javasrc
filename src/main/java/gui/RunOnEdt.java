package gui;

import javax.swing.SwingUtilities;

public class RunOnEdt {
	public static void main(String[] args) throws Exception {
		System.out.println("RunOnEdt.main()");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// UIManager.setLookAndFeel(
					//		UIManager.getSystemLookAndFeelClassName());
					javax.swing.JOptionPane.showMessageDialog(null, "Hello Java");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
