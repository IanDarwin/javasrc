import junit.framework.*;

/** Some JUnit test cases for the Singleton demo.
 * @version $Id$
 */
public class SingletonTest extends TestCase {

	Object d1, d2;

	/** setup method */
	public void setUp() {
		d1 = Singleton.getInstance();
		d2 = Singleton.getInstance();
	}

	/** constructor plumbing for junit */
	public SingletonTest(String name) {
		super(name);
	}

	public void testSingleness() { 
		assertTrue(d1 == d2);
	}

	public void testCorrectClass() {
		assertTrue(d1 instanceof Singleton);
	}

	/** This test will only compile if this test and Singleton remain
	 * in the same package.
	 */
	public void testConstruction() {
		try {
			Singleton impossible = Singleton.backdoor();
			fail("Singleton constructor should raise IllegalStateException");
		} catch (IllegalStateException ex) {
			System.out.println("Singleton Uniqueness Protection works OK");
			System.out.println(ex);
		}
	}
}
