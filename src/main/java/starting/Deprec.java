package starting;

// tag::main[]
import java.util.Date;

/** Demonstrate deprecation warning */
public class Deprec {

	public static void main(String[] av) {

		// Create a Date object for May 5, 1986
		@SuppressWarnings("deprecation")
		Date d = 
			new Date(86, 04, 05);	// EXPECT DEPRECATION WARNING without @SuppressWarnings
		System.out.println("Date is " + d);
	}
}
// end::main[]
