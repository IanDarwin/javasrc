package websvcdemo;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

/**
 * A real grunge client using a URLConnection - no toolkit!
 * Note that this is NOT considered "best practice" by a long shot! :-)
 * Specifically for the Learning Tree 577 Catalog example,
 * but the basic format should work for any XML SOAP web service.
 */
public class ReallyRawClient {
	
	    public static void main(String[] args) throws Exception {
	    	String host = null;
	    	int port = -1;
	      	host = "127.0.0.1";
	      	port = 8080;
	      	URL url = new URL("http", host, port, "/catalog/catalog.ws");
	        URLConnection conn = url.openConnection();
	        // Condition it to be a POST
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.setAllowUserInteraction(true);
	        // Condition it to be an XML web service
	        conn.setRequestProperty("Content-type", "text/xml");
	        	        
	        conn.connect();
		
	        System.out.println("Sending POST body");
	        PrintWriter out = new PrintWriter(conn.getOutputStream());
	        out.println("<?xml version='1.0' encoding='UTF-8'?>");
	        out.println("<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>");
	        out.println("<soap:Body>");
	        out.println("<cat:findCatalogItem xmlns:cat='http://webapps.catalog.rf.com/'>");
	        out.println("	<productId>3012</productId>");
	        out.println("</cat:findCatalogItem>");
	        out.println("</soap:Body>");
	        out.println("</soap:Envelope>");
	        out.close();

	        System.out.println("Reading and parsing response");
	        
	        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
	        DocumentBuilder parser = fact.newDocumentBuilder();
	        Document results = parser.parse(conn.getInputStream());

	        XPath xpath = XPathFactory.newInstance().newXPath();
            String titleExpr = "//title";
            Object title = xpath.evaluate(titleExpr, results);
            System.out.println("Found title: " + title);
            String priceExpr = "//price";
            Number price = (Number) xpath.evaluate(priceExpr, results, XPathConstants.NUMBER);
            System.out.println("Found price: " + price);
	        
	        System.out.println("Done");
	    }
}
 