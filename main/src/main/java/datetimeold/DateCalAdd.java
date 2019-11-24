package datetimeold;

import java.text.*;
import java.util.*;

/** DateCalAdd -- compute the difference between two dates.
 */
public class DateCalAdd {
	public static void main(String[] av) {
		/** Today's date */
		Calendar now = Calendar.getInstance();

		/* Do "DateFormat" using "simple" format. */
		SimpleDateFormat formatter
			= new SimpleDateFormat("E yyyy/MM/dd 'at' hh:mm:ss a zzz");
		System.out.println("It is now " + 
			formatter.format(now.getTime()));

		// Add a "# of years" increment to the existing Calendar object
		now.add(Calendar.YEAR, -2);
		
		System.out.println("Two years ago was " + 
			formatter.format(now.getTime()));
	}
}
