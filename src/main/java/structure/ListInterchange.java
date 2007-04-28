package structure;

import java.util.List;

public class ListInterchange {
	public static void interchange(List l, int i, int j) {
		if (i >= j) {
			throw new IllegalArgumentException("i and j must be in ascending order");
		}
		Object second = l.get(j);
		l.add(i, second);
		l.remove(j + 1);
	}
}
