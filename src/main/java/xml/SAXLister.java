import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.apache.xerces.parsers.SAXParser;

/** Simple lister - extract name and children tags from a user file.
 * Version for SAX 2.0
 * @author Ian Darwin
 * @version $Id$
 */
public class SAXLister {

	public static void main(String[] args) throws Exception { 
		XMLReader parser = XMLReaderFactory.createXMLReader(
			"org.apache.xerces.parsers.SAXParser");	// should load properties

		parser.setContentHandler(new PeopleHandler());

		parser.parse(args.length==1?args[0]:"parents.xml");
	}
}

class PeopleHandler extends DefaultHandler {

	boolean parent = false;
	boolean kids = false;

	public void startElement(String nsURI, String localName,
		String rawName, Attributes attributes)
	throws SAXException {

		com.darwinsys.util.Debug.println("docEvents",
			"startElement: " + localName + "," + rawName);

		// Consult rawName since we aren't using xmlns prefixes here.
		if (rawName.equalsIgnoreCase("name"))
			parent = true;
		if (rawName.equalsIgnoreCase("children"))
			kids = true;
	}

	public void characters(char[] ch, int start, int length) {
		if (parent) {
			System.out.println("Parent:  " + new String(ch, start, length));
			parent = false;
		} else if (kids) {
			System.out.println("Children: " + new String(ch, start, length));
			kids = false;
		}
	}

	/** Needed for parent constructor */
	public PeopleHandler() throws org.xml.sax.SAXException {
		super();
	}
}
