package io;

import java.io.*;

/**
 * Read a file containing an offset, and a String at that offset.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ReadRandom {
	final static String FILENAME = "random.dat";
	protected String fileName;
	protected RandomAccessFile seeker;

	public static void main(String[] argv) throws IOException {
		ReadRandom r = new ReadRandom(FILENAME);

		System.out.println("Offset is " + r.readOffset());
		System.out.println("Message is \"" + r.readMessage() + "\".");
	}

	/** Constructor: save filename, construct RandomAccessFile */
	public ReadRandom(String fname) throws IOException {
		fileName = fname;
		seeker = new RandomAccessFile(fname, "r");
	}

	/** Read the Offset field, defined to be at location 0 in the file. */
	public int readOffset() throws IOException {
		seeker.seek(0);				// move to very beginning
		return seeker.readInt();	// and read the offset
	}

	/** Read the message at the given offset */
	public String readMessage() throws IOException {
		seeker.seek(readOffset());	// move to the offset
		return seeker.readLine();	// and read the String
	}
}
// END main
