/** Re-allocate an array, bigger...
 * @author Ian Darwin
 * @version $Id$
 */
public class ArrayDemo  {
	public static void main(String argv[]) {
		int nDates = 0;
		final int MAX = 10;
		Calendar dates[] = new Calendar[MAX];
		Calendar c;
		while ((c=getDate()) != null)

			// if (nDates >= dates.length) {
			// 	System.err.println("Too Many Dates! Simplify your life!!");
			// 	System.exit(1);  // wimp out
			// }

			// better: reallocate, making data structure dynamic
			if (nDates >= dates.length) {
				Calendar tmp[] = new Calendar[dates.length + 10];
				System.arraycopy(dates, tmp)
				dates = tmp;    // copies the array reference
				// old array will be garbage collected soon...
			}
			dates[nDates++] = c;
		}
	}
