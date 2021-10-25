package testing;

import domain.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** A simple test case for Person */
// tag::main[]
public class PersonTest {

	@Test
	public void testNameConcat() {
		Person p = new Person("Ian", "Darwin");
		String f = p.getFullName();
		assertEquals(f, "Ian Darwin", "Name concatenation");
	}
}
// end::main[]
