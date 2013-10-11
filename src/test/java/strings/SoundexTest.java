package strings;

import junit.framework.TestCase;

/** Soundex Test program. Knuth's original examples, and mine. */
public class SoundexTest extends TestCase {

	/** JUnit test classes require this constructor */
	public SoundexTest(String name) {
		super(name);
	}

	public void testKnuth0() {
		assertEquals("E460", Soundex.soundex("Euler, Ellery"));
	}
	public void testKnuth1() {
		assertEquals("G200", Soundex.soundex("Gauss, Ghosh"));
	}
	public void testKnuth2() {
		assertEquals("H416", Soundex.soundex("Hilbert, Heilbronn"));
	}
	public void testKnuth3() {
		assertEquals("K530", Soundex.soundex("Knuth, Kant"));
	}
	public void testKnuth4() {
		assertEquals("L300", Soundex.soundex("Lloyd, Ladd"));
	}
	public void testKnuth5() {
		assertEquals("L222",
			Soundex.soundex("Lukasiewicz, Lissajous"));
	}
	public void testDarwin1() {
		assertEquals("D650", Soundex.soundex("Darwin"));
	}
	public void testDarwin2() {
		assertEquals("D653", Soundex.soundex("Darwent"));
	}
	public void testDarwin3() {
		assertEquals("D650", Soundex.soundex("Derwin"));
	}
}
