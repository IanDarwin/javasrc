package xml;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/** Read and write an XML document, preserving comments, using DOM
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class DocReadWriteDom {

	public static void main(String[] av) throws Exception {
		DocReadWriteDom dw = new DocReadWriteDom();
		Document doc = dw.makeDoc();

		System.out.println("Writing the tree now...");
		Transformer tx = TransformerFactory.newInstance().newTransformer();
		tx.setOutputProperty(OutputKeys.INDENT, "yes");
		tx.transform(new DOMSource(doc), new StreamResult(System.out));
	}

	/** Generate the XML document */
	protected Document makeDoc() throws Exception {
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			fact.setIgnoringComments(false);
			DocumentBuilder parser = fact.newDocumentBuilder();
			Document doc = parser.parse(new FileInputStream(new File("people.xml")));

			return doc;
	}
}
// END main
