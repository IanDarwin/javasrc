package structure;

import java.time.LocalDateTime;

/** Re-allocate an array, bigger...
 * @author Ian Darwin
 */
// tag::main[]
public class Array2  {
    public final static int INITIAL = 10,   // <1>
        GROW_FACTOR = 2;                    // <2>

    public static void main(String[] argv) {
        int nDates = 0;
        LocalDateTime[] dates = new LocalDateTime[INITIAL];
        StructureDemo source = new StructureDemo(21);
        LocalDateTime c;
        while ((c=source.getDate()) != null) {

			// Sub-optimal: give up
            // if (nDates >= dates.length) {
            //     throw new RuntimeException(
            //         "Too Many Dates! Simplify your life!!");
            // }

            // Better: reallocate, making data structure dynamic
            if (nDates >= dates.length) {
                LocalDateTime[] tmp = 
                    new LocalDateTime[dates.length * GROW_FACTOR];
                System.arraycopy(dates, 0, tmp, 0, dates.length);
                dates = tmp;    // copies the array reference
                // old array will be garbage collected soon...
            }
            dates[nDates++] = c;
        }
        System.out.println("Final array size = " + dates.length);
    }
}
// end::main[]
