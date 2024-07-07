package numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RomanNumberFormatTest {

	RomanNumberFormat nf;

	@BeforeEach
	void setUp() throws Exception {
		nf = new RomanNumberFormat();
	}

	@Test
	final void manyFormatLong() {
		assertThrows(NumberFormatException.class, () -> {
			nf.format(0);
			fail("Romans did not use Zero");
		});
	}

	@Test
	void tooBig() {
		assertThrows(NumberFormatException.class, () -> {
			nf.format(4000);
		});
	}

	@Test
	void thereThereAndBackAgain() {
		Long num1 = (Long) nf.parseObject("CLX", null);
		assertEquals(Long.valueOf(160), num1);
		Long num2 = (Long) nf.parseObject("XX", null);
		assertEquals(Long.valueOf(20), num2);
		long newVal = num1.intValue() * num2.intValue();
		System.out.println(newVal);
		String newString = nf.format(newVal);
		System.out.println(newString);
		Long num3 = (Long)nf.parseObject(newString, null);
		assertEquals(Long.valueOf(newVal), num3);
	}
}
