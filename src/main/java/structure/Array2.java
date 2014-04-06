package structure;

import java.util.Date;

/** Re-allocate an array, bigger...
 * @author Ian Darwin
 */
// BEGIN main
public class Array2  {
    public final static int INITIAL = 10,   // <1>
        GROW_FACTOR = 2;                    // <2>

    public static void main(String[] argv) {
        int nDates = 0;
        Date[] dates = new Date[INITIAL];
        StructureDemo source = new StructureDemo(21);
        Date c;
        while ((c=(Date)(source.getDate())) != null) {

            // if (nDates >= dates.length) {
            //     System.err.println("Too Many Dates! Simplify your life!!");
            //     System.exit(1);  // wimp out
            // }

            // better: reallocate, making data structure dynamic
            if (nDates >= dates.length) {
                Date[] tmp = new Date[dates.length * GROW_FACTOR];
                System.arraycopy(dates, 0, tmp, 0, dates.length);
                dates = tmp;    // copies the array reference
                // old array will be garbage collected soon...
            }
            dates[nDates++] = c;
        }
        System.out.println("Final array size = " + dates.length);
    }
}
// END main
