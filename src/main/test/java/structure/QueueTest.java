import junit.framework.*;

/** A simple test case for Queue */
public class QueueTest extends TestCase {

	/** JUnit test classes require this constructor */
	public QueueTest(String name) {
		super(name);
	}

	protected Queue q;

	public void setUp() {
		q = new Queue();
		q.q_add("One");
		q.q_add("Two");
	}

	public void testQueue() {
		assertEquals(q.q_take(), "One");
		assertEquals(q.q_take(), "Two");
		q.q_add("Three");
		assertEquals(q.q_take(), "Three");
		assert(q.size() ==  0);
		System.out.println("DONE");
	}
}
