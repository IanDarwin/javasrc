import com.sun.java.swing.*;
import com.sun.java.swing.tree.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Display a file system in a JTree view
 *
 * @version 0.3 1998/06/05, for Swing 1.0.1
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
		Collections.sort(ol, new StringIgnoreCaseComparator());
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
