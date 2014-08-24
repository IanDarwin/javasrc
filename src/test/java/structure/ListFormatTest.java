package structure;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListFormatTest {

	final static String[] UNFORMATTED = { "one", "two", "three" };
	final static String FORMATTED = "[\"one\", \"two\", \"three\"]";

	@Test
	public void testFormat() {
		assertEquals(FORMATTED, ListFormat.format(UNFORMATTED));
	}

	@Test
	public void tesetParse() {
		List<String> ret = ListFormat.parse(FORMATTED);
		assertEquals(3, ret.size());
		assertEquals(UNFORMATTED[0], ret.get(0));
		assertEquals(UNFORMATTED[2], ret.get(2));
	}
}
