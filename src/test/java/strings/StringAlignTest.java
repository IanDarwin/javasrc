package strings;

import junit.framework.TestCase;

/** StringAlign Test program.  */
public class StringAlignTest extends TestCase {

	String[] mesg = {"JavaFun", "JavaFun!" };

	/** The object being tested */
	protected StringAlign sa;

	/** JUnit test classes require(?) this constructor */
	public StringAlignTest(String name) {
		super(name);
	}

	public void testLeft() {
		for (int i=0; i<mesg.length; i++) {
			assertEquals(mesg[i].substring(0, 5),
				new StringAlign(5, StringAlign.JUST_LEFT).format(mesg[i]));
			String result = new StringAlign(10, StringAlign.JUST_LEFT).
					format(mesg[i]);
			assertEquals(mesg[i], result.trim());
			assertEquals(result.length(), 10);
		}
	}

	public void testCentre() {
		for (int i=0; i<mesg.length; i++) {
			assertEquals(mesg[i].substring(0, 5),
				new StringAlign(5, StringAlign.JUST_CENTER).format(mesg[i]).trim());
			assertEquals(" " + mesg[i],
				new StringAlign(10, StringAlign.JUST_CENTER).format(mesg[i]).substring(0, mesg[i].length()+1));
		}
	}

	public void testRight() {
		for (int i=0; i<mesg.length; i++) {
			System.out.println("Input String \"" + mesg[i] + "\"");
			assertEquals(mesg[i].substring(0, 5),
				new StringAlign(5, StringAlign.JUST_RIGHT).format(mesg[i]).trim());
			// XXX need to test this more.
			dump(StringAlign.JUST_RIGHT, 10,
				new StringAlign(10, StringAlign.JUST_RIGHT).format(mesg[i]));
		}
	}

	public void testNullString() {
		assertEquals("     ", new StringAlign(5, StringAlign.JUST_RIGHT).format(""));
	}

	// Consider phasing out this method.
	void dump(int x, int len, String mesg) {
		System.out.println(mesg);
	}
}
