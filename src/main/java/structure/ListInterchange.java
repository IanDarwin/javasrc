package structure;

import java.util.List;

public class ListInterchange {
	@SuppressWarnings({"rawtypes","unchecked"})
	public static void interchange(List list, int i, int j) {
		if (i == j) {
			throw new IllegalArgumentException("i and j must differ");
		}
		Object first = list.get(i);
		Object second = list.get(j);
		list.set(i, second);
		list.set(j, first);
	}
}
