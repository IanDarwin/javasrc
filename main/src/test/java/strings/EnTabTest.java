package strings;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test the guts of EnTab
 */
public class EnTabTest {

	EnTab t4, t8;

	@Before
	public void setUp() throws Exception {
		t4 = new EnTab(4);
		t8 = new EnTab();
	}

	@Test
	public void testGetTabSpacingDelegation() {
		assertEquals(4, t4.getTabSpacing());
		assertEquals(8, t8.getTabSpacing());
	}

	@Test
	public void testIdempotency() {
		assertEquals("", t4.entabLine(""));
		assertEquals("", t8.entabLine(""));
		assertEquals(" ", t4.entabLine(" "));
		assertEquals("   ", t4.entabLine("   "));
		assertEquals("abcde", t4.entabLine("abcde"));
	}

	@Test
	public void testLeadingTabs() {
		System.out.println(t4.entabLine("    A"));
		assertEquals("\tA", t4.entabLine("    A"));
	}

	@Test
	public void testEmbeddedTabs() {
		final String entebbe = t4.entabLine("    A   B");
		System.out.println("testEmbeddedTabs: result " + entebbe.replaceAll("\t", "<TAB>"));
		assertEquals("\tA\t B", entebbe);
	}
}
