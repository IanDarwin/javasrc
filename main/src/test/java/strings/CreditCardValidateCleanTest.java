package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditCardValidateCleanTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void clean() {
		assertEquals("12345", CreditCardValidate.clean("12345"));
		assertEquals("12345", CreditCardValidate.clean("1-23-45"));
		assertEquals("12345", CreditCardValidate.clean("12 -345"));
	}

}
