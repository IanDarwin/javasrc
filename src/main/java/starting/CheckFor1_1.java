package starting;

/** Check *by features* for JDK >= 1.1 */
public class CheckFor1_1 {
	public static void main(String[] args) {
		try {
			Class.forName("java.lang.reflect.Constructor");
			System.out.println("OK, pal, this JVM is 1.1 or later");
		} catch (ClassNotFoundException e) {
			String failure = 
				"Sorry, but this program needs a Java\n" +
				"Runtime based on Java JDK 1.1 or later";
			System.err.println(failure);
			throw new IllegalArgumentException(failure);
		}
	}
}
