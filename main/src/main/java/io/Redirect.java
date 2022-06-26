package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * "Redirect" or reassign some standard descriptors.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class Redirect {
	public static void main(String[] argv) throws IOException {
		// tag::main[]
		String LOGFILENAME = "error.log";
		System.setErr(new PrintStream(new FileOutputStream(LOGFILENAME)));
		System.out.println("Please look for errors in " + LOGFILENAME);
		// Now assume this is somebody else's code; you'll see it 
		//   writing to stderr...
		int[] a = new int[5];
		a[10] = 0;	// here comes an ArrayIndexOutOfBoundsException
		// end::main[]
	}
}
