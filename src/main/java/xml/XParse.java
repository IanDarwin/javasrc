import java.io.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/** Parse an XML file using DOM.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class XParse {

	/** Convert the file */
	public static void parse(String fileName, boolean validate) {
		try {
			System.err.println("Parsing " + fileName + "...");

			// Make the document a URL so relative DTD works.
			String uri = "file:" + new File(fileName).getAbsolutePath();

			XmlDocument doc = XmlDocument.createXmlDocument(uri, validate);
			System.out.println("Parsed OK");

		} catch (SAXParseException ex) {
			System.err.println("+================================+");
			System.err.println("|         *Parse Error*          |");
			System.err.println("+================================+");
			System.err.println("+ Line " + ex.getLineNumber ()
								+ ", uri " + ex.getSystemId ());
			System.err.println(ex.getClass());
			System.err.println(ex.getMessage());
			System.err.println("+================================+");
		} catch(SAXException ex) {
			System.err.println("+================================+");
			System.err.println("|         *SAX XML Error*        |");
			System.err.println("+================================+");
			System.err.println(ex.toString()); 
		} catch (IOException ex) {
			System.err.println("+================================+");
			System.err.println("|     *Input/Output Error*       |");
			System.err.println("+================================+");
			System.err.println(ex.toString());
		}
	}

	public static void main(String[] av) {
		if (av.length == 0) {
			System.err.println("Usage: XParse file");
			return;
		}
		boolean validate = false;
		for (int i=0; i<av.length; i++) {
			if (av[i].equals("-validate"))
				validate = true;
			else
				parse(av[i], validate);
		}
	}
}
