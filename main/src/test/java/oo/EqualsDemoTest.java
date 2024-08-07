package oo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// tag::main[]
/** Some JUnit test cases for EqualsDemo.
 * Writing a full set is left as "an exercise for the reader".
 */
class EqualsDemoTest {

	/** an object being tested */
	EqualsDemo d1;
	/** another object being tested */
	EqualsDemo d2;

	/** Method to be invoked before each test method */
	@BeforeEach
	void setUp() {
		d1 = new EqualsDemo();
		d2 = new EqualsDemo();
	}

	@Test
	void symmetry() {
		assertEquals(d1, d1);
	}

	@Test
	void symmetric() {
		assertTrue(d1.equals(d2) == d2.equals(d1));
	}

	@Test
	void caution() {
		assertNotEquals(null, d1);
	}
}
// end::main[]
