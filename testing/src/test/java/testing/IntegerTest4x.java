package testing;

import org.junit.*;

import static org.junit.Assert.*;


/** 
 * Demonstrate how you might use JUnit 4.x to test the java.lang.Integer 
 * class (this is not to say that Sun doesn't test before they ship it - they do!!).
 */
public class IntegerTest4x {

	@BeforeClass
	public static void setupClass() {
		System.out.println("IntegerTest: In demo Class setup method");
    }

	@Before
	public void setUp() {
		System.out.println("IntegerTest: In demo Instance setup method");
	}
	
	@Test
	public void testAdd() {
		assertEquals(4, 2 + 2);
	}

	@Test
	public void testDecode() throws Exception {
		int ret;
		ret = Integer.decode("-42").intValue();
		assertEquals(-42, ret);
		ret = Integer.decode("-0x42").intValue();
		assertEquals(-66, ret);
		try {
			Integer.decode("one two three");
			fail("IntegerTest: Did not throw expected NumberFormatException");
		} catch (NumberFormatException e) {
			System.out.println("IntegerTest: Caught expected exception");
		}
	}
}
