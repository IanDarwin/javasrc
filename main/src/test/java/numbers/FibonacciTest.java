package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FibonacciTest {

	int[] expect = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 };

	@Test
	void fibonacci() {
		for (int i = 0; i < expect.length; i++) {
			assertEquals(expect[i], Fibonacci.fibonacci(i), Integer.toString(i));
		}
	}

}
