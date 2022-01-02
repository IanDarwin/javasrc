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
		tester = new PerfectPangram(7);
	}
	
	@Test
	public void testPPGood() throws Exception {
		assertTrue(tester.pangram("acronym"));
		assertTrue(tester.pangram("camnory"));
		assertTrue(tester.pangram("abcdefg"));
		assertTrue(tester.pangram("harmony"));
		assertTrue(tester.pangram("rodwalk"));
	}

	public void testPPBad() throws Exception {
		assertFalse(tester.pangram("abecadarian"));	 // too long
		assertFalse(tester.pangram("acrimony"));	 // still too long
		assertFalse(tester.pangram("acronom"));	 // repeated letter
		assertFalse(tester.pangram("akron"));	 // too short
	}
}
