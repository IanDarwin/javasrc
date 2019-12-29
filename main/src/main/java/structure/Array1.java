package structure;

import java.time.LocalDate;

/** Review examples of arrays: shows array allocation, processing,
 * storing objects in Arrays,, two-dimensional arrays, and lengths.
 *
 * @author Ian Darwin
 */
// tag::main[]
public class Array1  {
	@SuppressWarnings("unused")
	public static void main(String[] argv) {
		int[] monthLen1;			// declare a reference
		monthLen1 = new int[12];		// construct it
		int[] monthLen2 = new int[12];	// short form
		// even shorter is this initializer form:
		int[] monthLen3 = {
				31, 28, 31, 30,
				31, 30, 31, 31,
				30, 31, 30, 31,
		};
		
		final int MAX = 10;
		LocalDate[] days = new LocalDate[MAX];
		for (int i=0; i<MAX; i++) {
			days[i] = LocalDate.of(2022, 02, i + 1);
		}
	 
		// Two-Dimensional Arrays
		// Want a 10-by-24 array
		int[][] me = new int[10][];
		for (int i=0; i<10; i++)
			me[i] = new int[24];

		// Remember that an array has a ".length" attribute
		System.out.println(me.length);
		System.out.println(me[0].length);

	}
}
// end::main[]
