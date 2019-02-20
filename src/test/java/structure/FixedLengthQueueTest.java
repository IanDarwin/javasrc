package structure;

import org.junit.*;

import static org.junit.Assert.*;

import java.time.LocalDate;

public class FixedLengthQueueTest {
	
	FixedLengthQueue<LocalDate> target;

	@Before
	public void setup() {
		target = new FixedLengthQueue<>(5);
	}

	@Test
	public void testPushPull() {
		LocalDate o = LocalDate.now();
		target.add(o);
		assertSame(o, target.peek());
		assertSame(o, target.poll());
		assertTrue(target.isEmpty());
	}
}
