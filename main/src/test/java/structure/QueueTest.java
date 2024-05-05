package structure;

// import static org.junit.jupiter.api.Assertions.super;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** A simple test case for Queue */
public class QueueTest {

	protected Queue<String> q;

	@BeforeEach
	void setUp() {
		q = new Queue<String>();
		q.q_add("One");
		q.q_add("Two");
	}

	@Test
	void queue() {
		assertEquals("One", q.q_take());
		assertEquals("Two", q.q_take());
		q.q_add("Three");
		assertEquals("Three", q.q_take());
		assertEquals(0, q.size());
		System.out.println("DONE");
	}
}
