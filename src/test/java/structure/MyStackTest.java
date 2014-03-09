package structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test for MyStack.
 * XXX Consider splitting off interface-method tests into a new
 * SimpleStackClass, parameterizing it to test both implementations.
 * @author Ian Darwin
 */
public class MyStackTest {
	SimpleStack<String> ss = new MyStack<String>();

	@Test(expected=IllegalArgumentException.class)
	public void invalidZeroLength() {
		new MyStack<Object>(0);
	}
	
	/** This test will have to be changed if the Stack gets
	 * modified to have internal expansion a la ArrayList
	 */
	@Test
	public void testPush() {
		int i = 0; 
		do {
			ss.push("Hello #" + i++);
		} while (((MyStack<String>) ss).hasRoom());
		assertEquals(i, MyStack.DEFAULT_INITIAL);
	}

	@Test
	public void testPop() {
		ss.push("Yowza");
		assertEquals(1, ((MyStack<String>) ss).getStackDepth());
		assertSame("Yowza", ss.pop());
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testPushOverflow() {
		MyStack<Integer> s = new MyStack<Integer>(1);
		s.push(0);
		s.push(0);
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testPopUnderflow() {
		ss.pop();
	}
	
	@Test
	public void testPeek() {
		ss.push("howdy mundo");
		assertEquals("howdy mundo", ss.peek());
	}
	
	@Test
	public void testPeekEmpty() {
		assertNull(ss.peek());
	}
	
	@Test
	public void testHasNext() {
		ss.push(null);
		assertEquals(1, ((MyStack<String>) ss).getStackDepth());
		assertTrue(((MyStack<String>) ss).hasNext());
	}
	
	@Test
	public void testHasRoom() {
		MyStack<Object> s = new MyStack<Object>(1);
		assertTrue(s.hasRoom());
		s.push(new Object());
		assertFalse(s.hasRoom());
	}


}
