import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

/** Make up and write an XML document, using DOM
 * UPDATED FOR JAXP.
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class DocWriteDOM {

	public static void main(String[] av) throws IOException {
		DocWriteDOM dw = new DocWriteDOM();
		Document doc = dw.makeDoc();

		// Sadly, the write() method is not in the DOM spec, so we
		// have to cast the Document to its implementing class
		// in order to call the Write method.
		//
		// WARNING
		//
		// This code therefore depends upon the particular
		// parser implementation.
		//
		((org.apache.crimson.tree.XmlDocument)doc).write(System.out);
	}

	/** Generate the XML document */
	protected Document makeDoc() {
		try {
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = fact.newDocumentBuilder();
			Document doc = parser.newDocument();

			Node root = doc.createElement("Poem");
			doc.appendChild(root);

			Node stanza = doc.createElement("Stanza");
			root.appendChild(stanza);
			
			Node line = doc.createElement("Line");
			stanza.appendChild(line);
			line.appendChild(doc.createTextNode("Once, upon a midnight dreary"));
			line = doc.createElement("Line");
			stanza.appendChild(line);
			line.appendChild(doc.createTextNode("While I pondered, weak and weary"));

			return doc;

		} catch (Exception ex) {
			System.err.println("+============================+");
			System.err.println("|        XML Error           |");
			System.err.println("+============================+");
			System.err.println(ex.getClass());
			System.err.println(ex.getMessage());
			System.err.println("+============================+");
			return null;
		}
	}
}
