package structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for MyStack.
 * XXX Consider splitting off interface-method tests into a new
 * SimpleStackClass, parameterizing it to test both implementations.
 * @author Ian Darwin
 */
class MyStackTest {
	SimpleStack<String> ss = new MyStack<String>();

	@Test
	void invalidZeroLength() {
		assertThrows(IllegalArgumentException.class, () -> {
			new MyStack<Object>(0);
		});
	}

	/** This test will have to be changed if the Stack gets
	 * modified to have internal expansion a la ArrayList
	 */
	@Test
	void push() {
		int i = 0; 
		do {
			ss.push("Hello #" + i++);
		} while (((MyStack<String>) ss).hasRoom());
		assertEquals(MyStack.DEFAULT_INITIAL, i);
	}

	@Test
	void pop() {
		ss.push("Yowza");
		assertEquals(1, ((MyStack<String>) ss).getStackDepth());
		assertSame("Yowza", ss.pop());
	}

	@Test
	void pushOverflow() {
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			MyStack<Integer> s = new MyStack<Integer>(1);
			s.push(0);
			s.push(0);
		});
	}

	@Test
	void popUnderflow() {
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			ss.pop();
		});
	}

	@Test
	void peek() {
		ss.push("howdy mundo");
		assertEquals("howdy mundo", ss.peek());
	}

	@Test
	void peekEmpty() {
		assertNull(ss.peek());
	}

	@Test
	void hasNext() {
		ss.push(null);
		assertEquals(1, ((MyStack<String>) ss).getStackDepth());
		assertTrue(((MyStack<String>) ss).hasNext());
	}

	@Test
	void hasRoom() {
		MyStack<Object> s = new MyStack<Object>(1);
		assertTrue(s.hasRoom());
		s.push(new Object());
		assertFalse(s.hasRoom());
	}


}
