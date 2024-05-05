package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the guts of EnTab
 */
class EnTabTest {

	EnTab t4, t8;

	@BeforeEach
	void setUp() throws Exception {
		t4 = new EnTab(4);
		t8 = new EnTab();
	}

	@Test
	void getTabSpacingDelegation() {
		assertEquals(4, t4.getTabSpacing());
		assertEquals(8, t8.getTabSpacing());
	}

	@Test
	void idempotency() {
		assertEquals("", t4.entabLine(""));
		assertEquals("", t8.entabLine(""));
		assertEquals(" ", t4.entabLine(" "));
		assertEquals("   ", t4.entabLine("   "));
		assertEquals("abcde", t4.entabLine("abcde"));
	}

	@Test
	void leadingTabs() {
		System.out.println(t4.entabLine("    A"));
		assertEquals("\tA", t4.entabLine("    A"));
	}

	@Test
	void embeddedTabs() {
		final String entebbe = t4.entabLine("    A   B");
		System.out.println("testEmbeddedTabs: result " + entebbe.replaceAll("\t", "<TAB>"));
		assertEquals("\tA\t B", entebbe);
	}
}
