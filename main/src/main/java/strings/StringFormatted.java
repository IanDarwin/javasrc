package strings;

/** Java 15 introduces instance method String.formatted(), which 
 * uses string as the format string and returns
 * the formatted String to allow chaining.
 */
public class StringFormatted {

	public static void main(String[] args) {
		String sNew = "%s %s".formatted("Hello", "World").toUpperCase();
		String sOld = "%s %s".formatted("Hello", "World").toUpperCase();
		System.out.println("sNew = " + sNew);
		System.out.println("sOld = " + sOld);
	}
}
