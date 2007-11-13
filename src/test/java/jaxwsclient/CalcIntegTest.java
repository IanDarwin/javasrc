package client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalcTest {

	@Test
	public void testAdd() {
		Calc client = new CalcService().getCalcPort();
		int result = client.add(34, 66);
		assertEquals(100, result);
	}

}
