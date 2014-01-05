package testing;

import static org.junit.Assert.assertThat;

import org.junit.Test;

// BEGIN main
public class HamcrestDemo {

	@Test
	public void testNameConcat() {
		Person p = new Person("Ian", "Darwin");
		String f = p.getFullName();
		assertThat(f, equalTo("Ian Darwin"));
	}
}
// END main
