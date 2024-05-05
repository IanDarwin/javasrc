package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TempConverterTest {

	@Test
	void coldIntersection() {
		assertEquals(-40d, TempConverter.fToC(-40), 0.001);
		assertEquals(-40d, TempConverter.cToF(-40), 0.001);
	}

	@Test
	void midIntersection() {
		assertEquals(10d, TempConverter.fToC(50), 0.001);
		assertEquals(50d, TempConverter.cToF(10), 0.001);
	}

	@Test
	void boilingPoint() {
		assertEquals(212d, TempConverter.cToF(100), .001);
		assertEquals(100d, TempConverter.fToC(212), .001);
	}
}
