package strings;

import junit.framework.TestCase;

/**
 * @author ian
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class EnTabTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(EnTabTest.class);
	}
	EnTab t4, t8;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		t4 = new EnTab(4);
		t8 = new EnTab();
		// For com.darwinsys.util.Debug: uncomment only 
		// "when you find yourself in times of trouble..."
		//System.setProperty("debug.tab", "true");
		//System.setProperty("debug.pad", "true");
		//System.setProperty("debug.space", "true");
	}

	public void testGetTabSpacingDelegation() {
		assertEquals(4, t4.getTabSpacing());
		assertEquals(8, t8.getTabSpacing());
	}
	
	public void testIdempotency() {
		assertEquals("", t4.entabLine(""));
		assertEquals("", t8.entabLine(""));
		assertEquals(" ", t4.entabLine(" "));
		assertEquals("abcde", t4.entabLine("abcde"));
	}
	
	public void testLeadingTabs() {
		System.out.println(t4.entabLine("    A"));
		assertEquals("\tA", t4.entabLine("    A"));
	}
}
