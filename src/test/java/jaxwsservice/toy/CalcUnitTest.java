package jaxwsservice.toy;

import static org.junit.Assert.assertEquals;

import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the CalcService. Absolutely requires Java 1.5+ and
 * JAX-WS 2.0 (which is included with Java SE 1.6+). 
 * Assumes client artifacts have been created, using e.g., on Unix:
 * $ mkdir jaxwsclient
 * $ wsimport -d jaxwsclient -keep 'http://localhost:8080/calc?wsdl'
 */
public class CalcUnitTest {

	Calc client;

	@Before
	public void initialize() {
		client = new Calc();
	}
	
	@Test
	public void testAdd() {
		int result = client.add(34, 66);
		assertEquals(100, result);
		
	}
	
	// Here it throws ArithmeticException
	@Test(expected=ArithmeticException.class)
	public void testDivide() {
		client.divide(99, 0); // should fail
	}

}
