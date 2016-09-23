package xml;

import java.io.File;
import org.apache.xml.resolver.tools.CatalogResolver;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/** Parse an XML file using DOM, via JAXP. 
 * Tries to handle both DTD-based and Schema-based validation.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class XParse {

	/**
	 * Parse one or more XML documents with optional validation.
	 * <b>Note:</b> It is a limitation of javax.xml.validation that parsing DTD-based documents can
	 * extract the DTD name/location and use it, whereas Schema(etc)-based validation requires the user to do
	 * this manually before invoking the parser.
	 * @param av Command args, may include -v for validation, and -a schema.xsd, before the filename(s);
	 * all documents parsed in this run must use the same Schema.
	 * @throws SAXException
	 */
	// BEGIN main
	public static void main(String[] av) throws SAXException {
		if (av.length == 0) {
			System.err.println("Usage: XParse file");
			return;
		}
		boolean validate = false;
		Schema schema = null;
		try {
			for (int i=0; i<av.length; i++) {
				if (av[i].equals("-v")) {
					validate = true;
				} else if (av[i].equals("-a")) {
					// "create a SchemaFactory capable of understanding W3C schemas"
					//   -- from the Javadoc page
					SchemaFactory schemaFactory = 
					SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

					// load the W3c XML schema, represented by a Schema instance
					String schemaLocation = av[++i];
					File schemaFile = new File(schemaLocation);
					if (!schemaFile.exists()) {
						throw new IOException(
						"Schema location = " + schemaLocation + " does not exist");
					}
					schema = schemaFactory.newSchema(schemaFile);
					
				} else {
					File xmlFile = new File(av[i]);
					System.err.println(
						"Parsing " + xmlFile.getAbsolutePath() + "...");
					
					DocumentBuilderFactory dbFactory = 
						DocumentBuilderFactory.newInstance();
					if (validate) {
						if (schema != null) {
							dbFactory.setSchema(schema);
						} else {
							dbFactory.setValidating(true);
							dbFactory.setNamespaceAware(true);
							dbFactory.setAttribute(
							"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
									XMLConstants.W3C_XML_SCHEMA_NS_URI);
						}			
					}
					DocumentBuilder parser = dbFactory.newDocumentBuilder();
					// If not using a specific schema, get local copies of DTDs/Schemas...
					if (schema == null) {
						EntityResolver res;
						res = new CatalogResolver();
						// res = new MyDTDResolver()
						parser.setEntityResolver(res);
					}
					parser.parse(xmlFile);
					System.out.println("Parsed/Validated OK");
				}
			}
		// Just +catch+ statements below here...
		// END main
		} catch (SAXParseException ex) {
			System.err.println("+================================+");
			System.err.println("|       *SAX Parse Error*        |");
			System.err.println("+================================+");
			System.err.println(ex.toString());
			System.err.println("At line " + ex.getLineNumber());
		} catch (Exception ex) {
			System.err.println("+================================+");
			System.err.println("|           *XML Error*          |");
			System.err.println("+================================+");
			ex.printStackTrace();
			
		}
	}
}
