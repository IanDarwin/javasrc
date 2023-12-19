package textproc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** 
 * JUnit test for Perfect Pangram
 */
public class PerfectPangramTest {

	PerfectPangram tester;

	@BeforeEach
	public void setUp() {
		tester = new PerfectPangram("camnory");
	}
	
	@Test
	public void testPPGood() {
		assertTrue(tester.pangram("acronym"));
		assertTrue(tester.pangram("camnory"));
	}

	@Test
	public void testPPBad() {
		assertFalse(tester.pangram("abcdefg"));
		assertFalse(tester.pangram("harmony"));
		assertFalse(tester.pangram("rodwalk"));
		assertFalse(tester.pangram("abecadarian"));	 // too long
		assertFalse(tester.pangram("acrimony"));	 // 'i' not in alphabet'
		assertFalse(tester.pangram("akron"));	 // too short

	}

	@Test
	public void testPerfect() {
		tester = new PerfectPangram("camnory", true);
		assertFalse(tester.pangram("acronom"));	 // repeated letter
	}

	@Test
	public void testPerfectWithFullAlphabet() {
		tester = new PerfectPangram(true);
		assertFalse(tester.pangram("aardvark"));	 // repeated letter
	}

	@Test
	public void testWithLongerAlphabet() {
		tester = new PerfectPangram("abcejklmru");
		assertTrue (tester.pangram("lumberjack"));
		assertFalse(tester.pangram("wolframite"));
	}

	@Test
	public void testFullAlphabet() {
		tester = new PerfectPangram(false);
		assertTrue(tester.pangramSentence("The quick brown foxes jumped over the lazy dog"));
		assertTrue(tester.pangramSentence("Few may back quirky judge who rips galvanized text"));
	}

	@Test
	public void testRepeatedLettersInAlphabet() {
		tester = new PerfectPangram("confetti");
		assertTrue(tester.pangram("confetti"));
	}
}
