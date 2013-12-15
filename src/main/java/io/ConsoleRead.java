package io;

/**
 * Read a name from the user; Requires Java 6 or later; does not run under Eclipse.
 */
// BEGIN main
public class ConsoleRead {
	public static void main(String[] args) {
		String name = System.console().readLine("What is your name?");
		System.out.println("Hello, " + name.toUpperCase());
	}
}
// END main
