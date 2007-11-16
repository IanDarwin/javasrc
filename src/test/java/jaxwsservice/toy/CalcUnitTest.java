package jaxwsclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the CalcService. Absolutely requires Java 1.5+ and
 * JAX-WS 2.0 (which ships with Java 1.6+). 
 * Assumes client artifacts have been created, using e.g., on Unix:
 * $ mkdir jaxwsclient
 * $ wsimport -d jaxwsclient -keep 'http://localhost:8080/calc?wsdl'
 */
public class CalcTest {

	Calc client;

	@Before
	public void initialize() {
		client = new CalcService().getCalcPort();
	}
	
	@Test
	public void testAdd() {
		int result = client.add(34, 66);
		assertEquals(100, result);
		
	}
	
	@Test
	public void testDivide() {
		try {
			client.divide(99, 0);
			fail("Did not throw exception for / 0");
		} catch (SOAPFaultException e) {
			System.out.println("testDivide: Caught expected exception");
		}
	}

}
