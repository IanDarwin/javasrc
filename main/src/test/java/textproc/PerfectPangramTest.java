package textproc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

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
}
