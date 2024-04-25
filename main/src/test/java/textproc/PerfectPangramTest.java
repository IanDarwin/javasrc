package textproc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** 
 * JUnit test for Perfect Pangram
 */
class PerfectPangramTest {

	PerfectPangram tester;

	@BeforeEach
	void setUp() {
		tester = new PerfectPangram("camnory");
	}

	@Test
	void pPGood() {
		assertTrue(tester.pangram("acronym"));
		assertTrue(tester.pangram("camnory"));
	}

	@Test
	void pPBad() {
		assertFalse(tester.pangram("abcdefg"));
		assertFalse(tester.pangram("harmony"));
		assertFalse(tester.pangram("rodwalk"));
		assertFalse(tester.pangram("abecadarian"));	 // too long
		assertFalse(tester.pangram("acrimony"));	 // 'i' not in alphabet'
		assertFalse(tester.pangram("akron"));	 // too short

	}

	@Test
	void perfect() {
		tester = new PerfectPangram("camnory", true);
		assertFalse(tester.pangram("acronom"));	 // repeated letter
	}

	@Test
	void perfectWithFullAlphabet() {
		tester = new PerfectPangram(true);
		assertFalse(tester.pangram("aardvark"));	 // repeated letter
	}

	@Test
	void withLongerAlphabet() {
		tester = new PerfectPangram("abcejklmru");
		assertTrue (tester.pangram("lumberjack"));
		assertFalse(tester.pangram("wolframite"));
	}

	@Test
	void fullAlphabet() {
		tester = new PerfectPangram(false);
		assertTrue(tester.pangramSentence("The quick brown foxes jumped over the lazy dog"));
		assertTrue(tester.pangramSentence("Few may back quirky judge who rips galvanized text"));
	}

	@Test
	void repeatedLettersInAlphabet() {
		tester = new PerfectPangram("confetti");
		assertTrue(tester.pangram("confetti"));
	}
}
