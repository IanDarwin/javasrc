package functional_med.data;

import java.time.LocalDateTime;

import functional_med.model.Reading;
import functional_med.model.ReadingType;

public class MockData {
	public static final Reading[] data = {
				new Reading(ReadingType.BG, LocalDateTime.of(2014, 06, 11, 4, 4), 5d, 0d),
				new Reading(ReadingType.BP, LocalDateTime.of(2014, 06, 11, 6, 16), 120, 80),
				new Reading(ReadingType.BG, LocalDateTime.of(2014, 06, 11, 8, 4), 3d, 0d),
				new Reading(ReadingType.BG, LocalDateTime.of(2014, 06, 11, 10, 4), 2.5d, 0d),
				new Reading(ReadingType.BP, LocalDateTime.of(2014, 06, 11, 12, 4), 120, 80d),
				new Reading(ReadingType.BG, LocalDateTime.of(2014, 06, 11, 10, 4), 18.1d, 0d),
		};
}
