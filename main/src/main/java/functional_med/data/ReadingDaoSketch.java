package functional_med.data;

import java.util.List;

import functional_med.model.Reading;

public interface ReadingDaoSketch {
	
	public List<Reading> findByType(); 
	public List<Reading> findByDate();
	public List<Reading> findBgReadingsOutOfRange(double min, double max);
	// the list goes on and on...
}
