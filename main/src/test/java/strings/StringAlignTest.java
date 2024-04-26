package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** StringAlign Test program.  */
class StringAlignTest {

	@Test
	void nullString() {
		assertEquals("     ", new StringAlign(5, StringAlign.Justify.RIGHT).format(""));
	}

	@Test
	void intRight() {
		assertEquals("   42",
				new StringAlign(5, StringAlign.Justify.RIGHT).format(42));
	}
	
	final String ONCE = "Once upon a";
	Object o = new Object() {
		public String toString() {
			return "Once upon a";
		}
	};

	@Test
	void objCentredOdd() {
		assertEquals("  " + ONCE + "  ",
				new StringAlign(15, StringAlign.Justify.CENTER).format(o));
	}

	@Test
	void objCentredEven() {
		assertEquals("  " + ONCE + "   ",
				new StringAlign(16, StringAlign.Justify.CENTER).format(o));
	}

	@Test
	void objCentred() {
		assertEquals("  " + ONCE + "  ",
				new StringAlign(15, StringAlign.Justify.CENTER).format(o));
	}
}
