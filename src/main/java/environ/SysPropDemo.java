import java.util.*;
/**
 * Demonstrate System Properties
 */
public class SysPropDemo {
	public static void main(String argv[]) {
		System.out.println("System Properties:");
		Properties p = System.getProperties();
		p.list(System.out);
		// Enumeration e = p.propertyNames();
		// while (e.hasMoreElements()) {
		// 	String s = (String)e.nextElement();
		// 	System.out.println(s + " = " + p.getProperty(s));
		// }
	}
}
