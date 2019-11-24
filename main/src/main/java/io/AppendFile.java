package io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * One way to append to a file in Java.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class AppendFile {
	private final File file;
	private final String FILENAME;

	public AppendFile() {
		try {
			file = File.createTempFile("foo", "bar");
			FILENAME = file.getAbsolutePath();
		} catch (IOException e) {
			throw new RuntimeException("IOException", e);
		}
	}

	public static void main(String[] argv) throws IOException {
		AppendFile x = new AppendFile();
		x.createInitialData();
		x.appendMoreData();
		x.report();
	}

	/** Read the message at the given offset */
	public void createInitialData() throws IOException {
		PrintWriter out = new PrintWriter(FILENAME);
		for (int i = 0; i < 5; i++) {
			out.println("Hello from line " + i);
		}
		out.close();
	}

	public void appendMoreData() throws IOException {
		RandomAccessFile seeker = 
			new RandomAccessFile(FILENAME, "rw");
		long readOffset = new File(FILENAME).length();
		seeker.seek(readOffset);	// move to the offset
		seeker.write("More Data\n".getBytes());// and write the String
		seeker.close();
	}

	public void report() {
		System.out.println("File is in " + FILENAME);
	}
}
