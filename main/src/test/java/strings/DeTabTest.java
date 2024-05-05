package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Simple tests for DeTab.
 */
public class DeTabTest {

	DeTab dt = new DeTab(8);

	@Test
	void idempotency() {
		assertEquals("", dt.detabLine(""));
		assertEquals(" a ", dt.detabLine(" a "));
	}

	@Test
	void detabLine() {
		assertEquals("       A", dt.detabLine("\tA"));
	}
}
