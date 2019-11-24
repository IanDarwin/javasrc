package structure;

import java.util.Collection;
import java.util.Properties;

import junit.framework.TestCase;

public class RandomPropsTest extends TestCase {

	public void testEmpty() {
		String ret = RandomProps.getRandomString(new Properties());
		assertEquals(RandomProps.EMPTY_PROPERTIES_MESSAGE, ret);
	}

	public void testOne() {
		Properties p = new Properties();
		p.put("foo", "bar");
		assertEquals("bar", p.getProperty("foo"));
	}

	public void testSystemProps() {
		Properties p = System.getProperties();
		Collection<Object> values = p.values();
		for (int i = 0; i < 10; i++) {
			String ret = RandomProps.getRandomString(p);
			assertNotNull(ret);
			assertTrue(values.contains(ret));
			// System.out.printf("Random Value %d = %s%n", i, ret);
		}
	}
}
