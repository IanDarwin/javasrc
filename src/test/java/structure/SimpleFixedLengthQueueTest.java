package structure;

import org.junit.*;

import static org.junit.Assert.*;

import java.time.LocalDate;

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
