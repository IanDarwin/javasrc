package testing;

/**
 * Demonstrate the new-in-1.4 "assert" facility.
 * <p>
 * In JDK1.4, assertions are not recognized by default; you must compile
 * with "javac -source 1.4 ...". And they are not enabled by default;
 * you must run with "java -enableassertions|-ea ...".
 * @since 1.4
 */
public class AssertDemo {
	public static void main(String[] args) {
		int i = 4;
		if (args.length == 1) {
			i = Integer.parseInt(args[0]);
		}
		assert i > 0 : "i is non-positive";
		System.out.println("Hello after an assertion");
	}
}
