package numbers;

import org.junit.jupiter.api.Test;

import static numbers.Factorial.factor;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

	@Test
	public void testFacts() {
		assertEquals(0, factor(0));
	}
}
