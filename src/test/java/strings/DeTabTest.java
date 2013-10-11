package strings;

import junit.framework.TestCase;

/**
 * Simple tests for DeTab.
 */
public class DeTabTest extends TestCase {

	DeTab dt = new DeTab(8);
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(DeTabTest.class);
	}

	public void testIdempotency() {
		assertEquals("", dt.detabLine(""));
		assertEquals(" a ", dt.detabLine(" a "));
	}
	
	public void testDetabLine(){
		assertEquals("       A", dt.detabLine("\tA"));
	}
}
