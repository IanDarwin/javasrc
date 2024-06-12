package structure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ListFormatTest {

	final static String[] UNFORMATTED = { "one", "two", "three" };
	final static String FORMATTED = "[\"one\", \"two\", \"three\"]";

	@Test
	void format() {
		assertEquals(FORMATTED, ListFormat.format(UNFORMATTED));
	}

	@Test
	void formatEmptyList() {
		assertEquals("[]", ListFormat.format(new ArrayList<String>()), "formatEmptyList");
	}

	@Test
	void parse() {
		List<String> ret = ListFormat.parse(FORMATTED);
		assertEquals(3, ret.size());
		assertEquals(UNFORMATTED[0], ret.getFirst());
		assertEquals(UNFORMATTED[2], ret.get(2));
	}

	@Test
	void parseEmpty() {
		List<String> ret = ListFormat.parse("[]");
		assertNotNull(ret);
		assertEquals(0, ret.size());
	}
}
