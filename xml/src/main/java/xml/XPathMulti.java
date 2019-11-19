package xml;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

/**
 * Another demo of XPath using JAXP, getting multiple elements rather than the first.
 */
public class XPathMulti {

	public static void main(String[] args) throws Exception {

		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		String doc = "<?xml version='1.0'?>" +
		"<section><sectiontitle>A Discourse of Numbers</sectiontitle>" +
		"<sectionnumber>1.2</sectionnumber>" +
		"<sectionnumber>1.3</sectionnumber>" +
		"<SC>Introduction</SC><p></p></section>";
		// parse() requires an InputStream, will not accept a Reader
		Document document = parser.parse(new ByteArrayInputStream(doc.getBytes()));

		// evaluate the XPath expression against the Document
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/section/sectionnumber";
		NodeList list = (NodeList)xpath.evaluate(
			expression, document, XPathConstants.NODESET);
		for (int i = 0; i < list.getLength(); i++) {
			Object secNum = list.item(i);
			System.out.printf("Sect num = %s (a %s)", secNum, secNum.getClass());
		}
	}
}
