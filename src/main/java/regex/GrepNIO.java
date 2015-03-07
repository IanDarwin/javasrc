package regex;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.regex.*;

/** A grep-like program using NIO but NOT LINE BASED.
 * Pattern and file name(s) must be on command line.
 */
// BEGIN main
public class GrepNIO {
	public static void main(String[] args) throws IOException {

		if (args.length < 2) {
			System.err.println("Usage: GrepNIO patt file [...]");
			System.exit(1);
		}

		Pattern p=Pattern.compile(args[0]);
		for (int i=1; i<args.length; i++)
			process(p, args[i]);
	}

	static void process(Pattern pattern, String fileName) throws IOException {

		// Get a FileChannel from the given file.
		FileInputStream fis = new FileInputStream(fileName);
		FileChannel fc = fis.getChannel();

		// Map the file's content
		ByteBuffer buf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

		// Decode ByteBuffer into CharBuffer
		CharBuffer cbuf =
			Charset.forName("ISO-8859-1").newDecoder().decode(buf);

		Matcher m = pattern.matcher(cbuf);
		while (m.find()) {
			System.out.println(m.group(0));
		}
		fis.close();
	}
}
// END main
