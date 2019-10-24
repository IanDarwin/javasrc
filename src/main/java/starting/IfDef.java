package starting;

/** Simple test to show if compilers exclude code that can't be reached. */
public class IfDef {
	public static void main(String[] argv) {
		final boolean DEBUG = false;
		System.out.println("Hello, World");
		if (DEBUG)
			System.out.println("Life is a voyage, not a destination.");
	}
}
