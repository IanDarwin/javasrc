import java.util.*;

/** Show use of Calendar objects */
public class GregCalDemo {

	public static void main(String av[]) {

		GregorianCalendar d1 = new GregorianCalendar(1986, 04, 05); // May 5
		GregorianCalendar d2 = new GregorianCalendar();	// today
		Calendar d3 = Calendar.getInstance();	// today

		System.out.println("It was then " + d1.getTime());
		System.out.println("It is now " + d2.getTime());
		System.out.println("It is now " + d3.getTime());
	}
}
