import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/** Convert a simple XML file to text.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class XmlToText {
	protected Reader is;
	protected String fileName;

	/** Construct a converter given an input filename */
	public XmlToText(String fn) {
		fileName = fn;
	}

	/** Convert the file */
	public void convert() {
	try {
		Parser instance = org.xml.sax.helpers.ParserFactory.makeParser();
		DocumentHandler handler;
		instance.setDocumentHandler(
			handler = new com.sun.xml.tree.XmlDocumentBuilder());
		is = new BufferedReader(new FileReader(fileName));
		instance.parse(new InputSource(is));
		XmlDocument doc = ((XmlDocumentBuilder)handler).getDocument();
		// For now, just dump it:
		// doc.write(System.out);
		TreeWalker tw = new TreeWalker(doc);
		for (Node p = tw.getCurrent(); p != null; p = tw.getNext())
			System.out.println(p);
	} catch (Exception ex) {
		System.err.println(ex);
	}
	}

	public static void main(String av[]) {
		System.out.println("Starting...");
		if (av.length == 0) {
			System.err.println("Usage: XmlForm file");
			return;
		}
		new XmlToText(av[0]).convert();
		System.out.println("All done!");
	}
}
