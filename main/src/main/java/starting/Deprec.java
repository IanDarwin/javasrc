package starting;

// tag::main[]
import java.util.Date;

/** Demonstrate deprecation warning */
public class Deprec {

	public static void main(String[] av) {

		// Create a Date object for June 6, 2014
		// @SuppressWarnings("deprecation") // Uncomment to suppress
		// EXPECT DEPRECATION WARNING without @SuppressWarnings
		Date d = new Date(2014-1900, 6-1, 10);
		// Shows why java.time.LocalDate.of(2014,06,10) is safer!
		System.out.println("Date is " + d);
	}
}
// end::main[]
