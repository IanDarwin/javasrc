package xml;

import java.io.File;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * List an XML file after building it into a JDOM Document.
 * Notice it is easier than using SAX or DOM directly.
 */
public class JDOMLister {
	public static void main(String[] args) { 
		try {
			SAXBuilder b = new SAXBuilder(true);	// true -> validate

			// Create a JDOM document.
			Document doc = b.build(new File("people+dtd.xml"));

			// Create an output formatter, and have it write the doc.
			new XMLOutputter().output(doc, System.out);
		} catch (JDOMException jex) {
			System.out.print("PARSE ERROR: " + jex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
