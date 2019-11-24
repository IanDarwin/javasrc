package structure;

import java.util.ArrayList;
import java.util.List;

public class ListAndBoxGenerics {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();

		// Populate the list...
		list.add(100);
		list.add(123);
		list.add(1);

		for (int i : list)
			System.out.println(i);
	}
}
