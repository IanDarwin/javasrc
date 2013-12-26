package strings;

// BEGIN main
public class ForEachChar {
	public static void main(String[] args) {
		String s = "Hello world";
		// for (char ch : s) {...} Does not work, in Java 7
		for (char ch : s.toCharArray()) {
			System.out.println(ch);
		}
	}
}
// END main
