package jaxwsclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Before;
import org.junit.Test;

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
