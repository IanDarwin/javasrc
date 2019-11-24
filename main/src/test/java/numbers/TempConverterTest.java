package numbers;

import static org.junit.Assert.*;

import org.junit.*;

public class TempConverterTest {

	@Test
	public void testColdIntersection() {
		assertEquals(-40d, TempConverter.fToC(-40), 0.001);
		assertEquals(-40d, TempConverter.cToF(-40), 0.001);
	}
	
	@Test
	public void testMidIntersection() {
		assertEquals(10d, TempConverter.fToC(50), 0.001);
		assertEquals(50d, TempConverter.cToF(10), 0.001);
	}
	
	@Test
	public void testBoilingPoint() {
		assertEquals(212d, TempConverter.cToF(100), .001);
		assertEquals(100d, TempConverter.fToC(212), .001);
	}
}
