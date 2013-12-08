package strings;

import static org.junit.Assert.*;

import org.junit.Test;

/** StringAlign Test program.  */
public class StringAlignTest  {

	String[] mesg = {"JavaFun", "JavaFun!" };

	@Test
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

	@Test
	public void testCentre() {
		for (int i=0; i<mesg.length; i++) {
			assertEquals(mesg[i].substring(0, 5),
				new StringAlign(5, StringAlign.JUST_CENTER).format(mesg[i]).trim());
			assertEquals(" " + mesg[i],
				new StringAlign(10, StringAlign.JUST_CENTER).format(mesg[i]).substring(0, mesg[i].length()+1));
		}
	}

	@Test
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

	@Test
	public void testNullString() {
		assertEquals("     ", new StringAlign(5, StringAlign.JUST_RIGHT).format(""));
	}

	// Consider phasing out this method.
	private void dump(int x, int len, String mesg) {
		System.out.println(mesg);
	}
	
	@Test
	public void testIntRight() {
		assertEquals("   42",
				new StringAlign(5, StringAlign.JUST_RIGHT).format(42));
	}
	
	final String ONCE = "Once upon a";
	Object o = new Object() {
		public String toString() {
			return "Once upon a";
		}
	};
	
	@Test
	public void testObjCentred() {
		assertEquals("  " + ONCE + "  ",
				new StringAlign(15, StringAlign.JUST_CENTER).format(o));
	}
}
