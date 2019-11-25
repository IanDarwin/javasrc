package websvcdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

/**
 * An example of one way you might deploy SAAJ-based services.
 * Just to show that it can be done; this is NOT how to do things in practice!
 * @author Ian Darwin
 */
public class SAAJServlet extends HttpServlet {

	private static final long serialVersionUID = 3259506014314462493L;
	static MessageFactory fact = null;

	static {
		try {
			fact = MessageFactory.newInstance();
		} catch (Exception ex) {
			System.out.println("Caught exception: " + ex.toString());
		}
	}

	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			InputStream is = request.getInputStream();
			Enumeration h = request.getHeaderNames();
			MimeHeaders headers = new MimeHeaders();
			while (h.hasMoreElements()) {
				String key = (String)h.nextElement();
				headers.addHeader(key, request.getHeader(key));
			}
			SOAPMessage incoming = fact.createMessage(headers, is);
			System.out.println(incoming);
			
			// Now we have the message in "incoming", process it...
			SOAPEnvelope envelope = incoming.getSOAPPart().getEnvelope();
			SOAPHeader header = envelope.getHeader();
			SOAPBody body = envelope.getBody();
			
			// These are just here to suppress Eclipse warnings; REPLACE
			// these printlns with your actual message handling code...
			System.out.println(header);
			System.out.println(body);
			
			// Now make up a reply.
			SOAPMessage outbound = fact.createMessage();
			
			// Fill it in...
			
			// Send it as the response.
			response.setContentType("text/xml");
			outbound.writeTo(response.getOutputStream());
			
		} catch (SOAPException e) {
			response.setStatus(500);
			System.out.print("SAAJServlet.doPost(): caught: ");
			e.printStackTrace();
		}
	}
}
