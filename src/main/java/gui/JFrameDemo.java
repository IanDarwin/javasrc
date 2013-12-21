package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Just a Frame
 */
// BEGIN main
public class JFrameDemo extends JFrame {

	private static final long serialVersionUID = -3089466980388235513L;
	JButton quitButton;

	/** Construct the object including its GUI */
	public JFrameDemo() {
		super("JFrameDemo");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(quitButton = new JButton("Exit"));

		// Set up so that "Close" will exit the program, 
		// not just close the JFrame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// This "action handler" will be explained later in the chapter.
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
			
		pack();
		setLocation(500, 400);
	}
	public static void main(String[] args) {
		new JFrameDemo().setVisible(true);
	}
}
// END main
