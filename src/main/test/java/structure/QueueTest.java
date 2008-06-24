package structure;

import junit.framework.TestCase;

/** A simple test case for Queue */
public class QueueTest extends TestCase {

	/** JUnit test classes require this constructor */
	public QueueTest(String name) {
		super(name);
	}

	protected Queue<String> q;

	public void setUp() {
		q = new Queue<String>();
		q.q_add("One");
		q.q_add("Two");
	}

	public void testQueue() {
		assertEquals(q.q_take(), "One");
		assertEquals(q.q_take(), "Two");
		q.q_add("Three");
		assertEquals(q.q_take(), "Three");
		assertTrue(q.size() ==  0);
		System.out.println("DONE");
	}
}
