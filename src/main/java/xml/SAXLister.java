import java.io.*;
import org.xml.sax.*;

/** Simple lister - extract name and email tags from a user file.
 * @author Ian Darwin
 * @version $Id$
 */
public class SaxLister {

	class PeopleHandler extends HandlerBase {

		boolean name = false;
		boolean mail = false;

		public void startElement(String tagName, AttributeList attributes)
		throws SAXException {
			if (tagName.equalsIgnoreCase("name"))
				name = true;
			if (tagName.equalsIgnoreCase("email"))
				mail = true;
		}

		public void endElement(String name) {
			// nothing to do.
		}

		public void characters(char[] ch, int start, int length) {
			if (name) {
				System.out.println("Name:  " + new String(ch, start, length));
				name = false;
			} else if (mail) {
				System.out.println("Email: " + new String(ch, start, length));
				mail = false;
			}
		}
	}

	public void list() throws Exception {
		Parser prs = org.xml.sax.helpers.ParserFactory.makeParser();
		prs.setDocumentHandler(new PeopleHandler());
		prs.parse(
			com.sun.xml.parser.Resolver.createInputSource(
				new File("people.xml")));
	}

	public static void main(String[] args) throws Exception { 
		new SaxLister().list();
	}
}
