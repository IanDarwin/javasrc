package xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Simple demo of reading an XML file using DOM, validating parser,
 * handling XIncludes on the fly.
 */
public class DomDemoXInclude {

	public static void main(String[] args) throws Throwable {
		
		final DocumentBuilderFactory dbFactory = 
			DocumentBuilderFactory.newInstance();
		
		dbFactory.setNamespaceAware(true);
		dbFactory.setXIncludeAware(true);	// turn on xinclude
		final DocumentBuilder parser = dbFactory.newDocumentBuilder();

		final Document document = 
			parser.parse(new File("xml/people-xinclude.xml"));

		System.out.println("Parsed/Validated without Exceptions");
		
		// Use XPath to get list of Person from the Document
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/include-demo/people/person/name";
		NodeList list = (NodeList)xpath.evaluate(
			expression, document, XPathConstants.NODESET);
		for (int i = 0; i < list.getLength(); i++) {
			Node personName = list.item(i);
			System.out.printf("Person = %s (a %s)%n", personName.getTextContent(), personName.getClass());
		}
		
	}
}
