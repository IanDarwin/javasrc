package lang;

/**
 * See if Strings are shared in Java.
 * The thing is, it depends on whether you say 
 * "string" or new String("mystring");
 */
public class TwoStrings {
	public static void main(String[] argv) {
		String one = "A String";
		String two = "A String";
		String three = new String(one);
		compare(one, two);
		compare(two, three);
	}
	public static void compare(String one, String two) {
		System.out.println("Comparing...");
		if (one == two) {
			System.out.println("Strings are shared: " +
				one.hashCode() + ", " + two.hashCode());
		} else if (one.equals(two)) {
			System.out.println("At least the strings are equal: " +
				one.hashCode() + ", " + two.hashCode());
			System.out.println((Object)one);
			System.out.println(two);
		} else System.out.println("This is rather distressing, sir.");
		System.out.println();
	}
}
