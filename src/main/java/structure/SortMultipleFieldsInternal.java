package structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** "eternal sort" on multiple fields: descending by count, ascending by name
 */
public class SortMultipleFieldsInternal {

	/** implement "descending by count, ascending by name" sort policy */
	static Comparator<SortableData> myComparator = new Comparator<SortableData>() {

		public int compare(SortableData o1, SortableData o2) {
			if (o1.count < o2.count) {
				return +1;	// backwards for descending
			}
			if (o1.count > o2.count) {
				return -1;	// backwards for descending
			}

			// Else the counts are the same, compare based
			// on name, ascending
			return o1.name.compareTo(o2.name);
		}
	};

	public static void main(String[] unused) {
		List<SortableData> dataList = new ArrayList<SortableData>();
		dataList.add(new SortableData(4, "fish"));
		dataList.add(new SortableData(2, "meat"));
		dataList.add(new SortableData(4, "grain"));
		dataList.add(new SortableData(3, "chocolate"));

		Collections.sort(dataList, myComparator);

		for (int i=0; i<dataList.size(); i++)
			System.out.println(dataList.get(i));
	}
}
