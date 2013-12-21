package gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// BEGIN main
public class JFrameFlowLayout extends JFrame {
	public JFrameFlowLayout() {
		Container cp = getContentPane();

		// Make sure it has a FlowLayout layoutmanager.
		cp.setLayout(new FlowLayout());

		// now add Components to "cp"...
		cp.add(new JLabel("Wonderful?"));
		cp.add(new JButton("Yes!"));
		pack();
	}

	// We need a main program to instantiate and show.
	public static void main(String[] args) {
		new JFrameFlowLayout().setVisible(true);
	}
}
// END main
