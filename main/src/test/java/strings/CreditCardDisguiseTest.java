package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CreditCardDisguiseTest {

	@Test
	void normal() {
		assertEquals("XXXXXXXXXXXX1234",
				CreditCardDisguise.disguise("9876543210981234"));
	}

	@Test
	void boundary() {
		assertEquals("XXXXXXXXXXXXXXXX5678",
				CreditCardDisguise.disguise("11112222333344445678"));
	}

	@Test
	void tooShort() {
		assertThrows(IllegalArgumentException.class, () -> {
			CreditCardDisguise.disguise("12345");
		});
	}

	@Test
	void tooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			CreditCardDisguise.disguise("11112222333344445555+");
		});
	}
}
