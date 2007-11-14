package xml;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;

/**
 * Simple demo of reading an XML file using DOM, validating parser.
 */
public class DomDemo {

	public static void main(String[] args) throws Throwable {
		
		final DocumentBuilderFactory dbFactory = 
			DocumentBuilderFactory.newInstance();
		
		dbFactory.setNamespaceAware(true);
		final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		final Schema schema = schemaFactory.newSchema(new File("xml/people.xsd"));
		dbFactory.setSchema(schema);
		final DocumentBuilder parser = dbFactory.newDocumentBuilder();

		final Document document = 
			parser.parse(new File("xml/people-schema.xml"));

		System.out.println("Parsed/Validated without Exceptions");
		
		System.out.println(document);
		
	}
}
