package jaxwsclient;

import static org.junit.Assert.assertEquals;

import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration test for CalcService. Absolutely requires Java 1.5+ and
 * JAX-WS 2.0 (which is included with Java SE 1.6+). 
 * Assumes client artifacts have been created, using e.g., on Unix:
 * $ mkdir jaxwsclient
 * $ wsimport -d jaxwsclient -keep 'http://localhost:9090/calc?wsdl'
 * (changed the package names after running this).
 */
public class CalcIntegTest {

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
	
	@Test(expected=SOAPFaultException.class)
	public void testDivide() {
		client.divide(99, 0); // should fail
	}

}
