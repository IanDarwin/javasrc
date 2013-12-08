package xml;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/** Make up and write an XML document, using JDOM
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class DocWriteJDOM {

	public static void main(String[] av) throws Exception {
		DocWriteJDOM dw = new DocWriteJDOM();
		Document doc = dw.makeDoc();
		// Create an output formatter, and have it write the doc.
		new XMLOutputter().output(doc, System.out);
	}

	/** Generate the XML document */
	protected Document makeDoc() throws Exception {
			Document doc = new Document(new Element("Poem"));
			doc.getRootElement().
				addContent(new Element("Stanza").
					addContent(new Element("Line").
							setText("Once, upon a midnight dreary")).
					addContent(new Element("Line").
							setText("While I pondered, weak and weary")));

			return doc;
	}
}
