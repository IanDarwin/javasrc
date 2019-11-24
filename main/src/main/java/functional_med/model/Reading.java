package functional_med.model;

import java.time.LocalDateTime;

/** A q+d demo Reading class; this is not best practices! */
public class Reading {
	public ReadingType type;
	public LocalDateTime when;
	public double value1;
	public double value2;
	
	public Reading(ReadingType type, LocalDateTime when, double value1,
			double value2) {
		super();
		this.type = type;
		this.when = when;
		this.value1 = value1;
		this.value2 = value2;
	}

	@Override
	public String toString() {
		return type.name() + ": " + value1 + (value2 != 0 ? "," + value2 : "" + " at " + when);
	}
}
