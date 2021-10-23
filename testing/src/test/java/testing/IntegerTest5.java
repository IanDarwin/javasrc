package testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** 
 * Demonstrate how you might use JUnit 4.x to test the java.lang.Integer 
 * class (this is not to say that Sun/Oracle doesn't test before they ship it - they do!!).
 */
public class IntegerTest5 {

	@BeforeAll
	public static void setupClass() {
		System.out.println("IntegerTest: In demo Class setup method");
    }

	@BeforeEach
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
		assertThrows(NumberFormatException.class, () -> Integer.decode("one two three"));
	}
}
