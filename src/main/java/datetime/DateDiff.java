import java.util.Date;

/** DateDiff -- compute the difference between two dates.
 */
public class DateDiff {
	public static void main(String av[]) {
		Date d1 = new Date(81,03,24);
		Date d2 = new Date();

		long diff = d2.getTime() - d1.getTime();


		System.out.println("Difference between " + d2 + 
			" and " + d1 + " is " +
			(diff / (1000*60*60*24)) +
			" days.");
	}
}
