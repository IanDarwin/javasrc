import junit.framework.*;

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
			System.out.println("Input String \"" + mesg[i] + "\"");
			assertEquals(mesg[i].substring(0, 5),
				new StringAlign(5, StringAlign.JUST_LEFT).format(mesg[i]));
			assertEquals(mesg[i].trim(),
				new StringAlign(10, StringAlign.JUST_LEFT).format(mesg[i]));
		}
	}

	public void testCentre() {
		for (int i=0; i<mesg.length; i++) {
			System.out.println("Input String \"" + mesg[i] + "\"");
			dump(StringAlign.JUST_CENTER, 5,
				new StringAlign(5, StringAlign.JUST_CENTER).format(mesg[i]));
			dump(StringAlign.JUST_CENTER, 10,
				new StringAlign(10, StringAlign.JUST_CENTER).format(mesg[i]));
		}
	}

	public void testRight() {
		for (int i=0; i<mesg.length; i++) {
			System.out.println("Input String \"" + mesg[i] + "\"");
			dump(StringAlign.JUST_RIGHT, 5,
				new StringAlign(5, StringAlign.JUST_RIGHT).format(mesg[i]));
			dump(StringAlign.JUST_RIGHT, 10,
				new StringAlign(10, StringAlign.JUST_RIGHT).format(mesg[i]));
		}
	}

	void dump(int x, int len, String mesg) {
		System.out.println(mesg);
	}
}
