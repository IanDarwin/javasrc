package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

// tag::main[]
public class HamcrestDemo {

	@Test
	public void showGroupAssertions() {
		assertAll("Assert tests in a group",
			() -> assertEquals(2,3), // Guaranteed to fail, first, see if others run.
			() -> assertEquals(2,1+1),
			() -> assertEquals("Hi", "Highlighter".substring(0,2))
		);
	}
}
// end::main[]
