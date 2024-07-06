package strings;

/**
 * StringEscapes.java - show string escapes.
 * Note that they may not print correctly on all platforms.
 */
// tag::main[]
public class StringEscapes {
	public static void main(String[] argv) {
		System.out.println("Java Strings in action:");
		System.out.println("A bell (alarm) entered in Octal: \007");
		System.out.println("A tab key: <<<\t>>>");
		System.out.println("A newline: <<<\n>>>");
		System.out.println("A UniCode character: \u0207");
		System.out.println("A backslash character: \\");
	}
}
// end::main[]
