package structure;

import java.time.LocalDateTime;
import java.util.Calendar;

/** Dummy source of Objects, for structure demos.
 * @author Ian Darwin
 */
public class StructureDemo {

	/** The max number of Objects to return */
	private final int MAX;

	/** Construct a StructureDemo */
	StructureDemo(int m) {
		MAX = m;
	}

	int n;


	/* Dummy method to return a sequence of Date references,
	 */
	public LocalDateTime getDate() {
		if (n++ > MAX)
			return null;
		return LocalDateTime.now();
	}
}
