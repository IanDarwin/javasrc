package i18n;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EnumIntlTest {
	@Test
	public void testNorth() {
		assertEquals("North", EnumIntl.NORTH.getDescription());
	}
}
