package strings;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CreditCardValidateCleanTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testClean() {
		assertEquals("12345", CreditCardValidate.clean("12345"));
		assertEquals("12345", CreditCardValidate.clean("1-23-45"));
		assertEquals("12345", CreditCardValidate.clean("12 -345"));
	}

}
