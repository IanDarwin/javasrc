package strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** StringAlign Test program.  */
public class StringAlignTest  {

	@Test
	public void testNullString() {
		assertEquals("     ", new StringAlign(5, StringAlign.Justify.RIGHT).format(""));
	}
	
	@Test
	public void testIntRight() {
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
	public void testObjCentred() {
		assertEquals("  " + ONCE + "  ",
				new StringAlign(15, StringAlign.Justify.CENTER).format(o));
	}
}
