import java.text.*;
import java.util.*;

/** Show some date uses */
public class DateDemo {
    public static void main(String[] args) {
		Date dNow = new Date();

		/* Simple, Java 1.0 date printing */
		System.out.println("It is now " + dNow.toString());

		/* More correct Java 1.1 GregorianCalendar date handling */
		GregorianCalendar gcNow = new GregorianCalendar();
		int year = gcNow.get(Calendar.YEAR);
		System.out.println("This is year " + year + " Anno Domini");

		/* Fancier "DateFormat" using "simple"(?) format.
		 * See the javadoc for java.text.SimpleDateFormat.
		 */
		SimpleDateFormat formatter
			= new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		System.out.println("It is " + formatter.format(dNow));
	}
}
