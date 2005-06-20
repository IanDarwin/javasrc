package email;

import javax.mail.Folder;
import javax.swing.tree.DefaultMutableTreeNode;

/** A Mutable Tree Node that is also a Folder. */
public class FolderNode extends DefaultMutableTreeNode {
	Folder f;
	FolderNode(Folder f) {
		this.f = f;
	}
	public String toString() {
		return f.getName();
	}
}
