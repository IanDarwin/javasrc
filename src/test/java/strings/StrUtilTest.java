package strings;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReverseInPlace() {
		assertEquals("", StrUtil.reverseInPlace(""));
		assertEquals("olleh", StrUtil.reverseInPlace("hello"));	// odd length
		assertEquals("nellaf", StrUtil.reverseInPlace("fallen"));	// even
	}
	
	@Test
	public void testReverseNew() {
		assertEquals("", StrUtil.reverseNew(""));
		assertEquals("olleh", StrUtil.reverseNew("hello"));	// odd length
		assertEquals("nellaf", StrUtil.reverseNew("fallen"));	// even
	}

}
