package numbers;

import junit.framework.TestCase;

/**
 * Test cases for Complex
 * @author ian
 */
public class ComplexTest extends TestCase {
	public void testEquals() {
		Complex c = new Complex(Math.PI, -Math.PI);
		assertEquals("equality test", c, c);
	}
	
	public void testAdd() {
		Complex a = new Complex(3,-1);
		Complex b = new Complex(1, 5);
		Complex sum = Complex.add(a, b);
		assertEquals("add test", new Complex(4, 4), sum);
	}
	
	public void testMultiplyAndDivide() {
		Complex c = new Complex(3,  5);
		Complex d = new Complex(2, -2);
		Complex m = new Complex(16.0, 4.0);
		assertEquals(m, c.multiply(d));
		assertEquals(c, Complex.divide(m,d));
	}
	
	public void testToString() {
		assertEquals("toString Test", "3.4-5.6i", 
				new Complex(3.4, -5.6).toString());
	}
}
