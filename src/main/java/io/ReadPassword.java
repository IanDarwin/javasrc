package io;

import java.io.Console;

/**
 * Read a password from the user.
 * REQUIRES JAVA 6 or later.
 */
// BEGIN main
public class ReadPassword {
	public static void main(String[] args) {
		Console cons;
		if ((cons = System.console()) != null) {
			char[] passwd = null;
			try {
				passwd = cons.readPassword("Password:");
				// In real life you would send the password into authentication code
				System.out.println("Your password was: " + new String(passwd));
			} finally {
				// Shred this in-memory copy for security reasons
				if (passwd != null) {
					java.util.Arrays.fill(passwd, ' ');
				}
			}
		} else {
			throw new RuntimeException("No console, can't get password");
		}
	}
}
// END main
