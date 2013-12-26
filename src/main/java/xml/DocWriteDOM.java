package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/** Make up and write an XML document, using DOM
 * Updated for JAXP, using identity Transformer
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class DocWriteDOM {

	public static void main(String[] av) throws Exception {
		DocWriteDOM dw = new DocWriteDOM();
		Document doc = dw.makeDoc();

		System.out.println("Writing the tree now...");
		Transformer tx = TransformerFactory.newInstance().newTransformer();
		tx.setOutputProperty(OutputKeys.INDENT, "yes");
		tx.transform(new DOMSource(doc), new StreamResult(System.out));
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
// END main
