import java.awt.*;
import javax.swing.*;

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
