package xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DomDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Throwable {
		
		final DocumentBuilderFactory dbFactory = 
			DocumentBuilderFactory.newInstance();
		
		dbFactory.setValidating(true);
		dbFactory.setNamespaceAware(true);

		final DocumentBuilder parser = 
			dbFactory.newDocumentBuilder();

		final Document document = 
			parser.parse(new File("xml/people-schema.xml"));

		System.out.println("Parsed/Validated OK");
		
		System.out.println(document.getTextContent());
	}
}
