package strings;

import junit.framework.TestCase;


/**
 * @author ian
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TabsTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TabsTest.class);
	}

	private Tabs t4, t8;
	
	public void setUp() {
		t4 = new Tabs(4);
		t8 = new Tabs();
	}
	
	public void testGetTabSpacing() {
		assertEquals(t4.getTabSpacing(), 4);
		assertEquals(t8.getTabSpacing(), Tabs.DEFTABSPACE);
	}
	
	public  void testIsTabStop() {
		assertEquals(false, t4.isTabStop(0));
		assertEquals(false, t4.isTabStop(1));
		assertEquals(false, t4.isTabStop(2));
		assertEquals(true,  t4.isTabStop(3));
		assertEquals(false, t4.isTabStop(4));
		assertEquals(false, t4.isTabStop(5));
		assertEquals(false, t4.isTabStop(6));
		assertEquals(true,  t4.isTabStop(7));
		assertEquals(false, t4.isTabStop(8));
	}
}
