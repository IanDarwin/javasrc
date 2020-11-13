package testing;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class Junit5HamcrestTest {

	String str = "Don't we all like JUnit and TDD now?";

	@Test
	public void someTest() {
		assertThat("truth is truth", str, containsString("TDD"));
	}
}
