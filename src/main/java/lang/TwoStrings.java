/**
 * See if Strings are shared on this implementation.
 */
public class TwoStrings {
	public static void main(String argv[]) {
		String one = "A String";
		String two = new String(one);
		if (one == two) {
			System.out.println("Strings are shared: " +
				one.hashCode() + ", " + two.hashCode());
		} else if (one.equals(two)) {
			System.out.println("At least the strings are equal: " +
				one.hashCode() + ", " + two.hashCode());
			System.out.println((Object)one);
			System.out.println(two);
		} else System.out.println("This is rather distressing, sir.");
	}
}
