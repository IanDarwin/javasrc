package testing;

import org.junit.*;
import static org.junit.Assert.*;

// BEGIN main
public class HamcrestDemo {

	@Test
	public void testNameConcat() {
		Person p = new Person("Ian", "Darwin");
		String f = p.getFullName();
		assertThat("Name concatenation", f, equals("Ian Darwin"));
	}
}
// END main
