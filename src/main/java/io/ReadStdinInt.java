package io;

import java.io.*;
/**
 * Read an int from Standard Input
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ReadStdinInt {
	public static void main(String[] ap) {
		String line = null;
		int val = 0;
		try {
			BufferedReader is = new BufferedReader(
				new InputStreamReader(System.in));
			line = is.readLine();
			val = Integer.parseInt(line);
			System.out.println("I read this number: " + val);
		} catch (NumberFormatException ex) {
			System.err.println("Not a valid number: " + line);
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}
	}
}
// END main
