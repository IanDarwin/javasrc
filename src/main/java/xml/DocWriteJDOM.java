import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

/** Make up and write an XML document, using JDOM
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
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
				addChild(new Element("Stanza")).
				addChild(new Element("Line").
						setContent("Once, upon a midnight dreary")).
				addChild(new Element("Line").
						setContent("While I pondered, weak and weary"));

			return doc;
	}
}
