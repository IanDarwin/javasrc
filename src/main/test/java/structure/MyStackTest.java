package structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

/**
 * JUnit test for MyStack.
 * @author ian
 */
public class MyStackTest {
	MyStack<String> ss = new MyStack<String>();
	MyStack<Date> sd = new MyStack<Date>();

	@Test(expected=IllegalArgumentException.class)
	public void invalidZeroLength() {
		new MyStack<Object>(0);
	}
	
	@Test
	public void testPush() {
		int i = 0; 
		do {
			ss.push("Hello #" + i++);
		} while (ss.hasRoom());
		assertEquals(i, MyStack.MAX);
	}

	@Test
	public void testPop() {
		ss.push("Yowza");
		assertEquals(1, ss.getStackDepth());
		assertSame("Yowza", ss.pop());
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testOverflow() {
		MyStack<Integer> s = new MyStack<Integer>(1);
		s.push(0);
		s.push(0);
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testUnderflow() {
		ss.pop();
	}
	
	@Test
	public void testHasNext() {
		ss.push(null);
		assertEquals(1, ss.getStackDepth());
		assertTrue(ss.hasNext());
	}
	
	@Test
	public void testHasRoom() {
		MyStack<Object> s = new MyStack<Object>(1);
		assertTrue(s.hasRoom());
		s.push(new Object());
		assertFalse(s.hasRoom());
	}


}
