import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 * Display a file system in a JTree view
 *
 * @version $Id$
 * @author Ian Darwin
 */
public class FileTree extends JPanel 
{ 
	/** Construct a FileTree */
    public FileTree(File dir) {
		setLayout(new BorderLayout());

		// Make a tree list with all the nodes, and make it a JTree
		JTree tree = new JTree(addNodes(null, dir));

		// Lastly, put the JTree into a JScrollPane.
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.getViewport().add(tree);
		add("Center", scrollpane);
    }

	/** Add nodes from under "dir" into curTop. Highly recursive. */
	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
		String curPath = dir.getPath();
		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
		if (curTop != null) {	// should only be null at root
			curTop.add(curDir);
		}
		String fl[] = dir.list();
		// TODO String fl2[] = sortedStringArray(fl);
		// Better yet, make two passes, one for Dirs and one for Files
		// Or maybe insert Dirs at top while adding files.
		File f;
		for (int i=0; i<fl.length; i++) {
			String newPath;
			if (curPath.equals("."))
				newPath = fl[i];
			else
				newPath = curPath + File.separator +fl[i];
			if ((f = new File(newPath)).isDirectory())
				addNodes(curDir,  f);
			else
				curDir.add(new DefaultMutableTreeNode(fl[i]));
		}
		return curDir;
	}

	public Dimension getMinimumSize() {
		return new Dimension(200, 400);
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 400);
	}

	/** Main: make a Frame, add a FileTree */
    public static void main(String av[]) {

		Frame frame = new Frame("FileTree");
		frame.setForeground(Color.black);
		frame.setBackground(Color.lightGray);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		frame.setLayout(new BoxLayout(frame, BoxLayout.X_AXIS));
		if (av.length == 0)
			frame.add(new FileTree(new File(".")));
		else for (int i=0; i<av.length; i++)
			frame.add(new FileTree(new File(av[i])));
		frame.pack();
		frame.setVisible(true);
	}
}
