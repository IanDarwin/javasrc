package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test cases for Complex
 * @author ian
 */
class ComplexTest {
	@Test
	void equals() {
		Complex c = new Complex(Math.PI, -Math.PI);
		assertEquals(c, c, "equality test");
	}

	@Test
	void add() {
		Complex a = new Complex(3,-1);
		Complex b = new Complex(1, 5);
		Complex sum = Complex.add(a, b);
		assertEquals(new Complex(4, 4), sum, "add test");
	}

	@Test
	void multiplyAndDivide() {
		Complex c = new Complex(3,  5);
		Complex d = new Complex(2, -2);
		Complex m = new Complex(16.0, 4.0);
		assertEquals(m, c.multiply(d));
		assertEquals(c, Complex.divide(m,d));
	}

	@Test
	void testToString() {
		assertEquals("3.4-5.6i", 
				new Complex(3.4, -5.6).toString(), 
				"toString Test");
	}
}
