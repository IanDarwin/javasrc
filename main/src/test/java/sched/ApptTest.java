package sched;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

import org.junit.Test;

/** Confirm that Appts compare, sort, print, and read back correctly.
 */
public class ApptTest {

	@Test
	public void testFactoryAndGetters() {
		
		// Exercise static factory method.
		Appt a1 = Appt.fromString("1951 4 24 6 0 birthday");
		assertEquals("fromString and getters", 1951, a1.getYear());
		assertEquals("fromString and getters",    4, a1.getMonth());
		assertEquals("fromString and getters",   24, a1.getDay());
		assertEquals("fromString and getters",    6, a1.getHour());
		assertEquals("fromString and getters",    0, a1.getMinute());
		assertEquals("fromString and getters", "birthday", a1.getText());
	}

	@Test
	public void testFactoryFailures() {

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
	public void testConstructorAndComparisons() {
		TreeSet<Appt> days =  new TreeSet<Appt>();
		// Exercise compareTo() method by inserting duplicates
		// into the TreeSet - ensure that only one copy is added.
		days.add(new Appt(1999, 1, 23, 12, 10, "go home"));
		days.add(new Appt(1999, 1, 23, 12, 10, "go home"));
		assertEquals("compareto", 1, days.size());

		Appt a = new Appt(2001, 2, 3, 1, 2, "testing 1 2 3");

		assertEquals("a is a", a, a);	
		
		assertEquals("fromString(toString()) idempotent", 
			Appt.fromString(a.toString()), a);
	}

	
	@Test
	public void testEquals() {
		Appt a1 = new Appt(2020,11,4,9,00, "Vote");
		Appt a2 = new Appt(
			LocalDate.of(2020, 11, 4),
			LocalTime.of(9, 00),
			"Vote");

		assertEquals("equals constructed both ways", a1, a2);
	}

	@Test
	public void testCompareTo() {
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
	public void testCompareInvolvingNullTime() {
		Appt a1 = new Appt(LocalDate.of(2020,11,4), null, "Vote");
		Appt a2 = new Appt(2020,11,4,9,00, "Vote");
		assertTrue(a1.compareTo(a2) < 0);
		
		Appt b1 = new Appt(LocalDate.of(2020,11,4), null, "Vote");
		Appt b2 = new Appt(LocalDate.of(2020,11,4), null, "Go Vote");
		assertTrue(b1.compareTo(b2) > 0);
	}
}
