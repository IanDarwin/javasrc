import java.io.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/** Subclass XML Tree Walker to use the provided TreeWalker
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class XTWalker extends XTW {

	public static void main(String av[]) {
		if (av.length == 0) {
			System.err.println("Usage: XTWalker file [...]");
			return;
		}
		for (int i=0; i<av.length; i++) {
			String name = av[i];
			new XTWalker().convert(name, true);
		}
	}

	/* Process all the nodes, recursively. */
	protected void doRecursive(Node p) {
		TreeWalker tw = new TreeWalker(p);
		Node n;
		while ((n = (Node)tw.getNext()) != null) {

			doNode(n);

		}
	}
}
