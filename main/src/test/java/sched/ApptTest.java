package sched;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

/** Confirm that Appts compare, sort, print, and read back correctly.
 */
class ApptTest {

	@Test
	void factoryAndGetters() {
		
		// Exercise static factory method.
		Appt a1 = Appt.fromString("1951 4 24 6 0 birthday");
		assertEquals(1951, a1.getYear(), "fromString and getters");
		assertEquals(4, a1.getMonth(), "fromString and getters");
		assertEquals(24, a1.getDay(), "fromString and getters");
		assertEquals(6, a1.getHour(), "fromString and getters");
		assertEquals(0, a1.getMinute(), "fromString and getters");
		assertEquals("birthday", a1.getText(), "fromString and getters");
	}

	@Test
	void factoryFailures() {

		try {
			Appt.fromString(null);
			fail("did not throw expected NullPointerException");
		} catch (NullPointerException e) {
			// OK
		}

		try {
			Appt.fromString("random garbage");
			fail("did not throw expected IllegalArumentException");
		} catch (IllegalArgumentException e) {
			// OK
		}

		try {
			Appt.fromString("1951 foo 24 6 0 birthday");
			fail("did not throw expected IllegalArumentException");
		} catch (IllegalArgumentException e) {
			// OK
		}
	}

	@Test
	void constructorAndComparisons() {
		TreeSet<Appt> days =  new TreeSet<Appt>();
		// Exercise compareTo() method by inserting duplicates
		// into the TreeSet - ensure that only one copy is added.
		days.add(new Appt(1999, 1, 23, 12, 10, "go home"));
		days.add(new Appt(1999, 1, 23, 12, 10, "go home"));
		assertEquals(1, days.size(), "compareto");

		Appt a = new Appt(2001, 2, 3, 1, 2, "testing 1 2 3");

		assertEquals(a, a, "a is a");	
		
		assertEquals(Appt.fromString(a.toString()), a, "fromString(toString()) idempotent");
	}


	@Test
	void equals() {
		Appt a1 = new Appt(2020,11,4,9,00, "Vote");
		Appt a2 = new Appt(
			LocalDate.of(2020, 11, 4),
			LocalTime.of(9, 00),
			"Vote");

		assertEquals(a1, a2, "equals constructed both ways");
	}

	@Test
	void compareTo() {
		Appt a1 = new Appt(2020,11,4,9,00, "Vote");
		Appt a2 = new Appt(2020,11,4,9,00, "Vote");
		assertEquals(0, a1.compareTo(a2));
		a1 = new Appt(2019, 06, 13, 9, 00, "Vote");
		assertTrue(a1.compareTo(a2) < 0);
		a1 = new Appt(2020, 11, 4, 8, 00, "Vote");
		assertTrue(a1.compareTo(a2) < 0);
		a1 = new Appt(2020, 11, 4, 9, 00, "Go Vote");
		assertTrue(a1.compareTo(a2) < 0);
	}

	@Test
	void compareInvolvingNullTime() {
		Appt a1 = new Appt(LocalDate.of(2020,11,4), null, "Vote");
		Appt a2 = new Appt(2020,11,4,9,00, "Vote");
		assertTrue(a1.compareTo(a2) < 0);
		
		Appt b1 = new Appt(LocalDate.of(2020,11,4), null, "Vote");
		Appt b2 = new Appt(LocalDate.of(2020,11,4), null, "Go Vote");
		assertTrue(b1.compareTo(b2) > 0);
	}
}
