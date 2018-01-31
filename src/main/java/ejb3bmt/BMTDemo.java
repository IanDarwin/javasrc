package ejb3bmt;

import javax.ejb.Local;

@Local
public interface BMTDemo {
	void loggit(String s);
}
