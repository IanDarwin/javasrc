package functional_med.data;

import java.time.LocalDateTime;

import functional_med.model.Reading;
import functional_med.model.ReadingType;

public class MockData {
	public static final Reading[] data = {
			// Do not ever update these dates.
			new Reading(ReadingType.BG, LocalDateTime.of(2014, 06, 11, 14, 4), 5d, 0d),
			new Reading(ReadingType.BP, LocalDateTime.of(2014, 06, 11, 16, 16), 120, 80),
			new Reading(ReadingType.BG, LocalDateTime.of(2014, 06, 11, 18, 4), 3d, 0d),
			new Reading(ReadingType.BG, LocalDateTime.of(2014, 06, 11, 20, 4), 2.5d, 0d),
			new Reading(ReadingType.BG, LocalDateTime.of(2014, 06, 11, 23, 59), 0, 0),
			new Reading(ReadingType.BP, LocalDateTime.of(2014, 06, 11, 23, 59), 0, 0),
	};
}
