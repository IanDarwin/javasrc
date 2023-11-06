package functional_med.data;

import java.time.LocalDate;
import java.util.List;

import functional_med.model.Reading;
import functional_med.model.ReadingType;

public interface ReadingDaoSketch {
	
	public List<Reading> findByType(ReadingType type);
	public List<Reading> findByDate(LocalDate date);
	public List<Reading> findByDateRange(LocalDate start, LocalDate end);
	public List<Reading> findBgReadingsOutOfRange(double min, double max);
	// the list goes on and on...
}
