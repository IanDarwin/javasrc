/** Check *by features* for JDK >= 1.1 */
public class TestFor1_1 {
	public static void main(String[] args) {
		try {
			Class.forName("java.lang.reflect.Constructor");
		} catch (ClassNotFoundException e) {
			String failure = 
				"Sorry, but this program needs a Java\n" +
				"Runtime based on Java JDK 1.1 or later";
			System.err.println(failure);
			throw new IllegalArgumentException(failure);
		}
		System.out.println("OK, pal, this JVM is 1.1 or later");
	}
}
