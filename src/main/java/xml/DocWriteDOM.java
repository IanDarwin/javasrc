import java.io.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/** Make up and write an XML document, using DOM
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class DocWriteDOM {

	public static void main(String[] av) throws IOException {
		DocWriteDOM dw = new DocWriteDOM();
		XmlDocument doc = dw.makeDoc();
		doc.write(System.out);
	}

	/** Generate the XML document */
	protected XmlDocument makeDoc() {
		try {
			XmlDocument doc = new XmlDocument();

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
