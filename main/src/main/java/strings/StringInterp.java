package strings;

/**
 * Basic demo of the StringTemplate processing, new in Java 21.
 */
// tag::main[]
public class StringInterp {
	final static int Z = 123;
	final static String A_STATIC_STRING = "My favorite number is " + Z;

	void main() {
		var a = 42;
		var b = Math.PI;
		var c = "I like";

		String result = "Hello! " + c + b + " so much I'd take " + a + "  of them! " + (a * b);

		System.out.println("Local String: " + result);

		System.out.println("Static String: " + A_STATIC_STRING);
	}
}
// end::main[]
