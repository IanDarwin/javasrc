package jaxwsservice.toy;

import javax.xml.ws.Endpoint;

/** Java 6 and later allow simple services to be
 * deployed using just the Java SE. So just run this!
 */
public class ServiceMain {
	
	public static void main(String[] args) {
		// Create what used to be called the "service stub"
		Calc impl = new Calc();
		// Start the service running
		Endpoint endPoint = 
			Endpoint.publish("http://localhost:8080/calc", impl);
		System.out.println("Listening on " + endPoint);
	}
}
