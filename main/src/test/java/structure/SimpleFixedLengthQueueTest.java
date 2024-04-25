package structure;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleFixedLengthQueueTest {
	
	SimpleFixedLengthQueue<LocalDate> target;

	@BeforeEach
	void setup() {
		target = new SimpleFixedLengthQueue<>(5);
	}

	@Test
	void pushPull() {
		LocalDate o = LocalDate.now();
		target.add(o);
		assertSame(o, target.peek(), "peek");
		assertSame(o, target.poll(), "poll");
		assertTrue(target.isEmpty(), "all gone");
	}
}
