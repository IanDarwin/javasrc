package structure;

import java.util.Arrays;

/** Demonstrate use of Arrays.sort on Booleans(!).
 * Try it, you'll get a ClassCastException, as there is no ordering of Booleans
 */
public class SortBooleans {
	public static void main(String[] unused) {
		Boolean[] data = {
			new Boolean(true),
			new Boolean(false),
			new Boolean(false),
			new Boolean(true),
		};
		Arrays.sort(data);		// EXPECT RUNTIME ERROR
		for (int i=0; i<data.length; i++)
			System.out.println(data[i]);
	}
}
