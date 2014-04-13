package io;

import java.io.*;

/**
 * "Redirect" or reassign some standard descriptors.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Redirect {
	public static void main(String[] argv) throws IOException {
		// BEGIN main
		String LOGFILENAME = "error.log";
		System.setErr(new PrintStream(new FileOutputStream(LOGFILENAME)));
		System.out.println("Please look for errors in " + LOGFILENAME);
		// Now assume this is somebody else's code; you'll see it 
		//   writing to stderr...
		int[] a = new int[5];
		a[10] = 0;	// here comes an ArrayIndexOutOfBoundsException
		// END main
	}
}
