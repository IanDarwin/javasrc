package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

/** Simple JFC JTree Simple application, showing default tree contents.
 * Note that the JTree will display a silly demo with not Model provided.
 */
public class JTreeSimple extends JFrame {
	JButton addButton, quitButton;
	JTree myTree;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a JTreeSimple object, tell it to show up
		new JTreeSimple().setVisible(true);
	}

	/** Construct the object including its GUI */
	public JTreeSimple() {
		super("JTreeSimple");
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		myTree = new JTree();

		JScrollPane scroller = new JScrollPane(myTree);
		cp.add(BorderLayout.CENTER, scroller);

		cp.add(BorderLayout.SOUTH, quitButton = new JButton("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
}
