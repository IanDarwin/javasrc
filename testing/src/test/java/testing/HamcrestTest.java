package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

// tag::main[]
/// This is really a Demo, not a Test...
public class HamcrestTest {

	@Test
	public void showGroupAssertions() {
		assertAll("Assert tests in a group",
		//	() -> assertEquals(2,3), // Sure to fail, first: see if others run.
		//	() -> assertEquals(3,1+1), // Will also run if uncommented, and fail
			() -> assertEquals(2,1+1),
			() -> assertEquals("Hi", "Highlighter".substring(0,2))
		);
	}
}
// end::main[]
