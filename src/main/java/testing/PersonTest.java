package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.Person;

/** A simple test case for Person */
// BEGIN main
public class PersonTest {

	@Test
	public void testNameConcat() {
		Person p = new Person("Ian", "Darwin");
		String f = p.getFullName();
		assertEquals("Name concatenation", "Ian Darwin", f);
	}
}
// END main
