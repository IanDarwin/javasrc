import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.regex.*;

// UNFINISHED
public class GrepNIO {
	public static void main(String[] args) throws IOException {

		// Get a FileChannel from the given file.
		FileChannel fc = new FileInputStream("infile").getChannel();
		// Map the file
		/*Mapped*/ByteBuffer buf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		// Decode ByteBuffer into CharBuffer
		CharBuffer cbuf =
			Charset.forName("ISO-8859-1").newDecoder().decode(buf);
	}
}
