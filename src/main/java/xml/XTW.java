import com.darwinsys.util.*;
import java.io.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/** XML Tree Walker
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class XTW {

	public static void main(String[] av) {
		if (av.length == 0) {
			System.err.println("Usage: XTW file [...]");
			return;
		}
		for (int i=0; i<av.length; i++) {
			String name = av[i];
			new XTW().convert(name, true);
		}
	}

	/** Convert the file */
	protected void convert(String fileName, boolean verbose) {
		Reader is;
		try {
			if (verbose)
				System.err.println(">>>Parsing " + fileName + "...");
			// Make the document a URL so relative DTD works.
			String uri = "file:" + new File(fileName).getAbsolutePath();
			XmlDocument doc = XmlDocument.createXmlDocument(uri);
			if (verbose)
				System.err.println(">>>Walking " + fileName + "...");
			doRecursive(doc);

		} catch (Exception ex) {
			System.err.println("+============================+");
			System.err.println("|        XTW Error           |");
			System.err.println("+============================+");
			System.err.println(ex.getClass());
			System.err.println(ex.getMessage());
			System.err.println("+============================+");
		}
		if (verbose) {
			System.err.println(">>>Done " + fileName + "...");
		}
	}

	/* Process all the nodes, recursively. */
	protected void doRecursive(Node p) {
		if (p == null) {
			return;
		}
		NodeList nodes = p.getChildNodes();
		Debug.println("xml-tree", "Element has " + numElem + " children");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (n == null) {
				continue;
			}

			doNode(n);

		}
	}

	protected void doNode(Node n) {

		switch(n.getNodeType()) {
			case Node.ELEMENT_NODE:
				System.out.println("ELEMENT<" + n.getNodeName() + ">");
				doRecursive(n);
				break;
			case Node.TEXT_NODE:
				String text = n.getNodeValue();
				if (text.length() == 0 || 
					text.equals("\n") || text.equals("\\r")) {
					break;
				}
				System.out.println("TEXT: " + text);
				break;
			default:
				System.err.println( "OTHER NODE " +
					n.getNodeType() + ": " + n.getClass());
				break;
		}
	}
}
