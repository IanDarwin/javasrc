package strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @author ian
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TabsTest {

	private Tabs t4, t8;

	@BeforeEach
	void setUp() {
		t4 = new Tabs(4);
		t8 = new Tabs();
	}

	@Test
	void getTabSpacing() {
		assertEquals(4, t4.getTabSpacing());
		assertEquals(Tabs.DEFTABSPACE, t8.getTabSpacing());
	}

	@Test
	void isTabStop() {
		assertFalse(t4.isTabStop(0));
		assertFalse(t4.isTabStop(1));
		assertFalse(t4.isTabStop(2));
		assertTrue(t4.isTabStop(3));
		assertFalse(t4.isTabStop(4));
		assertFalse(t4.isTabStop(5));
		assertFalse(t4.isTabStop(6));
		assertTrue(t4.isTabStop(7));
		assertFalse(t4.isTabStop(8));
	}
}
