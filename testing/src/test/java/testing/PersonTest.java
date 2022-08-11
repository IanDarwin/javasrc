package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Person;

/** A simple test case for Person */
// tag::main[]
public class PersonTest {

	Person p;
	
	@Test
	public void testNameConcat() {
		String act = p.getFullName();
		assertEquals(act, "Ian Darwin", "Name concatenation");
	}

	@BeforeEach
	private void setup() {
		p = new Person("Ian", "Darwin");
	}
}
// end::main[]
