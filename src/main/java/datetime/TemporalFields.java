package datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalField;

public class TemporalFields {

	public static void main(String[] args) {
		Instant now = Instant.now();
		System.out.println(now);
		
		LocalDate dt = LocalDate.of(1991,04,24);
		TemporalField field = null;
		//dt.get(field);
		
	}

}
