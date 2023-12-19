package functional_med.model;

import java.time.LocalDateTime;

/** A q+d demo Reading class */
public record Reading(ReadingType type, LocalDateTime when, double value1, double value2)
	implements Comparable<Reading> {

	@Override
	public String toString() {
		return type.name() + ": " + value1 + (value2 != 0 ? "," + value2 : "" + " at " + when);
	}

	@Override
	public int compareTo(Reading o) {
		return when.compareTo(o.when);
	}
}
