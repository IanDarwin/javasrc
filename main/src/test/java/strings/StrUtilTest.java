package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StrUtilTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void reverseInPlace() {
		assertEquals("", StrUtil.reverseInPlace(""));
		assertEquals("olleh", StrUtil.reverseInPlace("hello"));	// odd length
		assertEquals("nellaf", StrUtil.reverseInPlace("fallen"));	// even
	}

	@Test
	void reverseNew() {
		assertEquals("", StrUtil.reverseNew(""));
		assertEquals("olleh", StrUtil.reverseNew("hello"));	// odd length
		assertEquals("nellaf", StrUtil.reverseNew("fallen"));	// even
	}

}
