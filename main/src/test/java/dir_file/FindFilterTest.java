package dir_file;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class FindFilterTest {

	FindFilter target;
	
	@Before
	public void setUp() throws Exception {
		target = new FindFilter();
	}

	@Test
	public void testmakeNameFilter() {
		Pattern actual = target.makeNameFilter("Foo*.java");
		Pattern expected = Pattern.compile("Foo.*\\.java$");
		assertEquals("Convert Glob to Regex", expected.toString(), actual.toString());
	}

}
