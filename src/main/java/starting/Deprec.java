import java.util.Date;

/** Demonstrate deprecation warning */
public class Deprec {

	public static void main(String[] av) {

		// Create a Date object for May 5, 1986
		// EXPECT DEPRECATION WARNING
		Date d = new Date(86, 04, 05);		// May 5, 1986
		System.out.println("Date is " + d);
	}
}
