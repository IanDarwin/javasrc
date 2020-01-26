package jaxwsclient;

import static org.junit.Assert.assertEquals;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPFaultException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Integration test for CalcService. Absolutely requires Java 1.5+ and
 * JAX-WS 2.0 (which is included with Java SE 1.6+). 
 * Assumes client artifacts have been created, using e.g., on Unix:
 * $ mkdir jaxwsclient
 * $ wsimport -d jaxwsclient -keep 'http://localhost:7094/calc?wsdl'
 */
public class CalcIntegTest {

	static Endpoint endpoint;
	
	jaxwsclient.Calc client;
	
	@BeforeClass
	public static void startServer() {
		jaxwsservice.Calc impl = new jaxwsservice.Calc();
		// Start the service running
		endpoint = Endpoint.publish("http://localhost:7094/calc", impl);
	}
	
	@AfterClass
	public static void stopServer() {
		endpoint.stop();
	}

	@Before
	public void initialize() {
		client = new CalcService().getCalcPort();
	}
	
	@Test
	public void testAdd() {
		int result = client.add(34, 66);
		assertEquals(100, result);
	}
	
	@Test(expected=SOAPFaultException.class)
	public void testDivide() {
		client.divide(99, 0); // should fail
	}

}
