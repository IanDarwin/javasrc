package structure;

import junit.framework.TestCase;

/**
 * @version $Id$
 */
public class ToyStackTest extends TestCase {

	private ToyStack ts;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(ToyStackTest.class);
	}

	public void setUp(){
		ts = new ToyStack();
	}
	public void testPushAndPop() {
		int i = 123987;
		ts.push(i);
		assertEquals(i, ts.pop());
	}

	public void testPeek() {
		int i = 1066;
		ts.push(i);
		assertEquals(i, ts.peek());
	}

}
