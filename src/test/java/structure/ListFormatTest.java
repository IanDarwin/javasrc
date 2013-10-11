package structure;

import junit.framework.TestCase;

public class ListFormatTest extends TestCase {
	public void testFormat() {
		String[] data = { "one", "two", "three" };
		assertEquals("{one, two, three}", ListFormat.format(data));
	}
}
