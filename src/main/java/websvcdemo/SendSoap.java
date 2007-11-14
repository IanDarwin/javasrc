package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Sends a SOAPMessage to a Web service using a URL and URLConnection to send the
 * Web service's HTTP port and sending a POST request.; this is pretty much
 * what the SOAPConnection.call() method does.
 * @return
 *  The SOAP response, as a String so you can parse it how you like
 *  (JAXB, JDOM, DOM4J et. al. come to mind).
 */
public class SendSoap {
    
    public static String call(SOAPMessage message, String endpoint)
            throws IOException, SOAPException {

        // Create URL representing endpoint
        URL endpointURL = new URL(endpoint);
        URLConnection endpointConnection = endpointURL.openConnection();
        endpointConnection.setDoOutput(true);
        endpointConnection.setUseCaches(false);
        endpointConnection.addRequestProperty("SOAPAction", "\"\"");

        OutputStream requestStream = endpointConnection.getOutputStream();

        // Write the SOAPMessage to the web service
        message.writeTo(requestStream);
        requestStream.close();

        // Read the reply into a StringBuffer
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(endpointConnection.getInputStream()));
        StringBuffer response = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            String lineEnding = System.getProperty("line.separator");
            response.append(line).append(lineEnding);
        }
        reader.close();

        return response.toString();
    }
}
