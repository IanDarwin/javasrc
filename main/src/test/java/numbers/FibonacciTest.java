package numbers;

import junit.framework.TestCase;

public class FibonacciTest extends TestCase {

	int[] expect = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 };

	public void testFibonacci() {
		for (int i = 0; i < expect.length; i++) {
			assertEquals(Integer.toString(i), expect[i], Fibonacci.fibonacci(i));
		}
	}

}
