import junit.framework.TestCase;

/*
 * Created on Mar 7, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

/**
 * @author ian
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MyStackTest extends TestCase {
	MyStack ss;
	MyStack sd;
	
	public void setUp() {
		ss = new MyStack();
		sd = new MyStack();
	}
	public void testPush() {
		int i = 0; 
		do {
			ss.push("Hello #" + i++);
		} while (ss.hasRoom());
		assertEquals(i, MyStack.MAX)
	}

	public void testHasNext() {
	}

	public void testHasRoom() {
	}

	public void testPop() {
	}

}
