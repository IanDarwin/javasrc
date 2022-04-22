package structure;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class SimpleFixedLengthQueueTest {
	
	SimpleFixedLengthQueue<LocalDate> target;

	@Before
	public void setup() {
		target = new SimpleFixedLengthQueue<>(5);
	}

	@Test
	public void testPushPull() {
		LocalDate o = LocalDate.now();
		target.add(o);
		assertSame("peek", o, target.peek());
		assertSame("poll", o, target.poll());
		assertTrue("all gone", target.isEmpty());
	}
}
