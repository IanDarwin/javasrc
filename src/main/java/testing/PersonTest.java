import junit.framework.*;

/** A simple test case for Person */
public class PersonTest extends TestCase {

	/** JUnit test classes require this constructor */
	public PersonTest(String name) {
		super(name);
	}

	/** Simple test program. */
	public void testNameConcat() {
		Person p = new Person("Ian", "Darwin");
		String f = p.getFullName();
		if (!f.equals("Ian Darwin"))
			throw new IllegalStateException("Name concatenation broken");
		System.out.println("Fullname " + f + " looks good");
	}
}
