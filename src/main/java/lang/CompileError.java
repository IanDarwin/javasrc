package lang;

/**
 * Program with a syntax error, to show compiler error messages
 */
public class CompileError {
	public static void main(String argv]) {	// EXPECT COMPILE ERROR
		System.out.println("You won't see this in Java");
		System.out.println(System.getProperties());
	}
}
