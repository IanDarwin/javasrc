package dir_file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindFilterTest {

	FindFilter target;

	@BeforeEach
	void setUp() throws Exception {
		target = new FindFilter();
	}

	@Test
	void testmakeNameFilter() {
		Pattern actual = target.makeNameFilter("Foo*.java");
		Pattern expected = Pattern.compile("Foo.*\\.java$");
		assertEquals(expected.toString(), actual.toString(), "Convert Glob to Regex");
	}

}
