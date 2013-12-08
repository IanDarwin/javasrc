package lang;

import java.io.*;

/** 
 * Demo to show what happens if you try to add an exception to a method
 * that its superclass method of the same signature did not have.
 *
 * @author	Ian Darwin
 *
 */
public class AddException {
	/**
	 * Main program, test driver for ThreadsDemo1 class.
	 */
	public static void main(String[] argv) {
		new AddException();
	}

	public void run() {
	}
}

class AddException2 extends AddException {
	public void run() throws IOException {	// EXPECT COMPILE ERROR
	}
}

