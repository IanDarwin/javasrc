package websvcdemo;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/** Simple demo of Apache Axis 1 SOAP call
 * @author Adapted from Apache Axis example code.
 */
public class CallSoap {
	public static void main(String[] args) throws Throwable {

		Service service = new Service();
		Call theCall = (Call)service.createCall();
		URL url = new URL("http://nagoya.apache.org:5049/axis/services/echo");
		theCall.setTargetEndpointAddress(url);
		theCall.setOperationName(
			new QName("http://soapinterop.org/", "echoString"));

		final String message = "Hello, World";
		String ret = (String)theCall.invoke(new Object[] { message });

		if (ret.equals(message)) {
			System.out.printf("Sent %s and got it", message);
		} else {
			System.out.printf("Sent %s but got %s!", message, ret);
		}
	}
}
