import com.sun.java.swing.*;
import com.sun.java.swing.tree.*;

import java.awt.*;
import java.awt.event.*;

/** Simple JFC JTree demo */
public class Tree2 extends JFrame {
	JButton addButton, quitButton;
	JTree myTree;
	DefaultMutableTreeNode root, child;

	/** "main program" method - construct and show */
	public static void main(String av[]) {
		// create a Tree2 object, tell it to show up
		new Tree2().setVisible(true);
	}

	/** Construct the object including its GUI */
	public Tree2() {
		super("Tree2");
		setLayout(new BorderLayout());
			
		root = new DefaultMutableTreeNode("root");

		child = new DefaultMutableTreeNode("Colors"); 
		root.add(child);
		child.add(new DefaultMutableTreeNode("Cyan"));
		child.add(new DefaultMutableTreeNode("Magenta"));
		child.add(new DefaultMutableTreeNode("Yellow"));
		child.add(new DefaultMutableTreeNode("Black"));

		myTree = new JTree(root);

		add(BorderLayout.CENTER, myTree);

		add(BorderLayout.NORTH, addButton = new JButton("Add"));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			// Insert more nodes into the tree
			child = new DefaultMutableTreeNode("Flavors");
			child.add(new DefaultMutableTreeNode("Java"));
			child.add(new DefaultMutableTreeNode("Espresso"));
			child.add(new DefaultMutableTreeNode("Hey Joe!"));
			child.add(new DefaultMutableTreeNode("Charcoal"));
			child.add(new DefaultMutableTreeNode("Paint Remover"));

			// Notify the model, which will add it and create an event, and
			// send it up the tree...

			((DefaultTreeModel)myTree.getModel()).insertNodeInto(child, root, 0);
			}
		});

		add(BorderLayout.SOUTH, quitButton = new JButton("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		pack();
	}
}
