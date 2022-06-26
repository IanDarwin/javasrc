package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Read a file in character mode - maximally inefficient.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class CharMode {
	public static void main(String[] argv) throws IOException {
		// tag::main[]
		BufferedReader is = new BufferedReader(
			new InputStreamReader(System.in));

		int c;
		while ((c=is.read()) != -1) {
			System.out.print((char)c);
		}
		// end::main[]
	}
}
