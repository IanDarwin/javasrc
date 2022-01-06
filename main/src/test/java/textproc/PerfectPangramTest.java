package textproc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	public void testPPGood() throws Exception {
		assertTrue(tester.pangram("acronym"));
		assertTrue(tester.pangram("camnory"));
		assertFalse(tester.pangram("abcdefg"));
		assertFalse(tester.pangram("harmony"));
		assertFalse(tester.pangram("rodwalk"));
	}

	@Test
	public void testPPBad() throws Exception {
		assertFalse(tester.pangram("abecadarian"));	 // too long
		assertFalse(tester.pangram("acrimony"));	 // 'i' not in alphabet'
		assertFalse(tester.pangram("acronom"));	 // repeated letter
		assertFalse(tester.pangram("akron"));	 // too short
	}

	@Test
	public void testLonger() {
		tester = new PerfectPangram("abcejklmru");
		assertTrue (tester.pangram("lumberjack"));
		assertFalse(tester.pangram("wolframite"));
	}
}
