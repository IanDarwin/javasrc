import java.util.*;

/**
 * Demonstrate the Properties class
 */
public class PropsDemo {
	public static void main(String argv[]) {
		Properties p = new Properties();

		p.setProperty("Adobe", "Mountain View, CA");
		p.setProperty("IBM", "White Plains, NJ");
		p.setProperty("Learning Tree", "Los Angeles, CA");
		p.setProperty("Microsoft", "Redmond, WA");
		p.setProperty("Netscape", "Mountain View, CA");
		p.setProperty("Sun", "Mountain View, CA");
		p.list(System.out);
	}
}
