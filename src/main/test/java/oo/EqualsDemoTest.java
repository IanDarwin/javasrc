import junit.framework.*;

/** Some JUnit test cases for EqualsDemo.
 * Writing a full set is left as "an exercise for the reader".
 * Run as: $ java junit.textui.TestRunner EqualsDemoTest
 * @version $Id$
 */
public class EqualsDemoTest extends TestCase {

	/** an object being tested */
	EqualsDemo d1;
	/** another object being tested */
	EqualsDemo d2;

	/** init() method */
	public void setUp() {
		d1 = new EqualsDemo();
		d2 = new EqualsDemo();
	}

	/** constructor plumbing for junit */
	public EqualsDemoTest(String name) {
		super(name);
	}

	public void testSymmetry() { 
		assertTrue(d1.equals(d1));
	}

	public void testSymmetric() {
		assertTrue(d1.equals(d2) && d2.equals(d1));
	}

	public void testCaution() {
		assertTrue(!d1.equals(null));
	}
}
