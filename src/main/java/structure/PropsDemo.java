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

		// Now list the Properties, using System.out
		p.list(System.out);

		// The inverse operation: load it from a file
		// assume System.in has been redirected to "PropsDemo.dat"
		try {
			p.load(System.in);
		} catch (java.io.IOException exc) {
			System.err.println(exc);
			return;
		}

		// List the values again; notice the new ones joined the old
		p.list(System.out);
	}
}
