package i18n;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EnumIntlTest {
	@Test
	void north() {
		assertEquals("North", EnumIntl.NORTH.getDescription());
	}
}
