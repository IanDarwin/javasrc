import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

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

		// Add a listener
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node =
					(DefaultMutableTreeNode)e.getPath().getLastPathComponent();
				System.out.println("You selected " + node);
			}
		});

		// Lastly, put the JTree into a JScrollPane.
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.getViewport().add(tree);
		add(BorderLayout.CENTER, scrollpane);
    }

	/** Add nodes from under "dir" into curTop. Highly recursive. */
	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
		String curPath = dir.getPath();
		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
		if (curTop != null) {	// should only be null at root
			curTop.add(curDir);
		}
		Vector ol = new Vector();
		String[] tmp = dir.list();
		for (int i=0; i<tmp.length; i++)
			ol.addElement(tmp[i]);
		Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
		File f;
		Vector files = new Vector();
		// Make two passes, one for Dirs and one for Files. This is #1.
		for (int i=0; i<ol.size(); i++) {
			String thisObject = (String)ol.elementAt(i);
			String newPath;
			if (curPath.equals("."))
				newPath = thisObject;
			else
				newPath = curPath + File.separator + thisObject;
			if ((f = new File(newPath)).isDirectory())
				addNodes(curDir,  f);
			else
				files.addElement(thisObject);
		}
		// Pass two: for files.
		for (int fnum=0; fnum<files.size(); fnum++)
			curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
		return curDir;
	}

	public Dimension getMinimumSize() {
		return new Dimension(200, 400);
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 400);
	}

	/** Main: make a Frame, add a FileTree */
    public static void main(String[] av) {

		JFrame frame = new JFrame("FileTree");
		frame.setForeground(Color.black);
		frame.setBackground(Color.lightGray);
		Container cp = frame.getContentPane();

		if (av.length == 0) {
			cp.add(new FileTree(new File(".")));
		} else {
			cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));
			for (int i=0; i<av.length; i++)
				cp.add(new FileTree(new File(av[i])));
		}

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
