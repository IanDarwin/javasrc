import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.xml.sax.*;

/** Parse an XML file using DOM, via JAXP.
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

			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			if (validate)
				f.setValidating(true);
			DocumentBuilder p = f.newDocumentBuilder();
			// Get local copies of DTDs...
			p.setEntityResolver(new MyDTDResolver());
			Document doc = p.parse(uri);
			System.out.println("Parsed OK");

		} catch (SAXParseException ex) {
			System.err.println("+================================+");
			System.err.println("|       *SAX Parse Error*        |");
			System.err.println("+================================+");
			System.err.println(ex.toString());
			System.err.println("At line " + ex.getLineNumber());
			System.err.println("+================================+");
		} catch (SAXException ex) {
			System.err.println("+================================+");
			System.err.println("|          *SAX Error*           |");
			System.err.println("+================================+");
			System.err.println(ex.toString());
			System.err.println("+================================+");
		} catch (Exception ex) {
			System.err.println("+================================+");
			System.err.println("|           *XML Error*          |");
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
			if (av[i].equals("-v"))
				validate = true;
			else
				parse(av[i], validate);
		}
	}
}
