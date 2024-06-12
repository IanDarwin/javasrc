package structure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Properties;

import org.junit.jupiter.api.Test;

class RandomPropsTest {

	@Test
	void empty() {
		String ret = RandomProps.getRandomString(new Properties());
		assertEquals(RandomProps.EMPTY_PROPERTIES_MESSAGE, ret);
	}

	@Test
	void one() {
		Properties p = new Properties();
		p.put("foo", "bar");
		assertEquals("bar", p.getProperty("foo"));
	}

	@Test
	void systemProps() {
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
