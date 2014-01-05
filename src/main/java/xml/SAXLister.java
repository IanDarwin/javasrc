package xml;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Simple lister - extract person and email tags from a user file. 
 * @author Ian Darwin
 */
// BEGIN main
public class SAXLister {
	final boolean DEBUG = false;
	public static void main(String[] args) throws Exception {
		new SAXLister(args);
	}
	
	public SAXLister(String[] args) throws SAXException, IOException {
		XMLReader parser = XMLReaderFactory.createXMLReader();
		parser.setContentHandler(new PeopleHandler());
		parser.parse(args.length == 1 ? args[0] : "xml/people.xml");
	}
	
	/** Inner class provides DocumentHandler
	 */
	class PeopleHandler extends DefaultHandler {
		boolean person = false;
		boolean email = false;
		public void startElement(String nsURI, String localName,
				String rawName, Attributes attributes) throws SAXException {
			if (DEBUG) {
				System.out.println("startElement: " + localName + ","
					+ rawName);
			}
			// Consult rawName since we aren't using xmlns prefixes here.
			if (rawName.equalsIgnoreCase("name"))
				person = true;
			if (rawName.equalsIgnoreCase("email"))
				email = true;
		}
		public void characters(char[] ch, int start, int length) {
			if (person) {
				System.out.println("Person:  " +
					new String(ch, start, length));
				person = false;
			} else if (email) {
				System.out.println("Email: " + 
					new String(ch, start, length));
				email = false;
			}
		}
	}
}
// END main
