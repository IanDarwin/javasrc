package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/** Parse an XML file using DOM, via JAXP.
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class XParse {

	/** Parse the file 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException */
	public static void parse(String fileName, Schema schema, boolean validate) throws ParserConfigurationException, SAXException, IOException {

			System.err.println("Parsing " + fileName + "...");

			// Make the document a URL so relative DTD works.
			String uri = "file:" + new File(fileName).getAbsolutePath();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			if (validate || schema != null)
				factory.setValidating(true);
			if (schema != null) {
				factory.setSchema(schema);
			}
			DocumentBuilder p = factory.newDocumentBuilder();
			// Get local copies of DTDs...
			p.setEntityResolver(new MyDTDResolver());
			p.parse(uri);
			System.out.println("Parsed/Validated OK");
	}

	/**
	 * Parse one or more XML documents with optional validation.
	 * <b>Note:</b> It is an unpleasant limitation of javax.xml.validation that parsing of DTD-based documents can
	 * extract the DTD name/location and use it, whereas Schema(etc)-based validation requires the user to do
	 * this manually before invoking the parser.
	 * @param av Command args, may include -v for validation, and -a schema.xsd, before the filename(s);
	 * all must use the same Schema.
	 * @throws SAXException
	 */
	public static void main(String[] av) throws SAXException {
		if (av.length == 0) {
			System.err.println("Usage: XParse file");
			return;
		}
		boolean validate = false;
		Schema schema = null;
		try {
		for (int i=0; i<av.length; i++) {
			if (av[i].equals("-v"))
				validate = true;
			else if (av[i].equals("-a")) {
			    // create a SchemaFactory capable of understanding WXS schemas - from the Javadoc page
			    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			    // load the W3c XML schema, represented by a Schema instance
			    String schemaLocation = av[++i];
				File file = new File(schemaLocation);
				if (!file.exists()) {
					throw new IOException("Schema location = " + schemaLocation + " does not exist");
				}
				Source schemaFile = new StreamSource(file);
			    schema = factory.newSchema(schemaFile);
			    
			} else
				parse(av[i], schema, validate);
		}
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
			ex.printStackTrace();
			
		}
	}
}
