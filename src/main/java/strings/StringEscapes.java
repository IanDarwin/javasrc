package strings;

/**
 * StringEscapes.java - show string escapes.
 * Note that they may not print correctly on all platforms.
 */
// BEGIN main
public class StringEscapes {
	public static void main(String[] argv) {
		System.out.println("Java Strings in action:");
		// System.out.println("An alarm or alert: \a");	// not supported
		System.out.println("An alarm entered in Octal: \007");
		System.out.println("A tab key: \t(what comes after)");
		System.out.println("A newline: \n(what comes after)");
		System.out.println("A UniCode character: \u0207");
		System.out.println("A backslash character: \\");
	}
}
// END main
