import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import com.darwinsys.util.Debug;

/**
 * Simple lister - extract name and children tags from a user file. Version for
 * SAX 2.0
 * 
 * @author Ian Darwin
 * @version $Id$
 */
public class SAXLister {
	public static void main(String[] args) throws Exception {
		new SAXLister(args);
	}
	
	public SAXLister(String[] args) throws SAXException, IOException {
		XMLReader parser = XMLReaderFactory
				.createXMLReader();
		// should load properties rather than hardcoding class name
		parser.setContentHandler(new PeopleHandler());
		parser.parse(args.length == 1 ? args[0] : "people.xml");
	}
	
	/** Inner class provides DocumentHandler
	 */
	class PeopleHandler extends DefaultHandler {
		boolean person = false;
		boolean email = false;
		public void startElement(String nsURI, String localName,
				String rawName, Attributes attributes) throws SAXException {
			Debug.println("docEvents", "startElement: " + localName + ","
					+ rawName);
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
