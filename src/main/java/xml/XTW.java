package xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.darwinsys.util.Debug;

/** XML Tree Walker
 * UPDATED FOR JAXP.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
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
		
		try {
			if (verbose)
				System.err.println(">>>Parsing " + fileName + "...");
			// Make the document a URL so relative DTD works.
			String uri = "file:" + new File(fileName).getAbsolutePath();

			DocumentBuilderFactory factory =
				DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse( uri );
 
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
		Debug.println("xml-tree", "Element has " + 
			nodes.getLength() + " children");
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
// END main
