package io;

import java.io.*;
import java.util.zip.*;

/**
 * Read some data from a gzip file.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ReadGZIP {
	public static void main(String[] argv) throws IOException {
		String FILENAME = "file.txt.gz";

		// Since there are 4 constructor calls here, I wrote them out in full.
		// In real life you would probably nest these constructor calls.
		FileInputStream fin = new FileInputStream(FILENAME);
		GZIPInputStream gzis = new GZIPInputStream(fin);
		InputStreamReader xover = new InputStreamReader(gzis);
		BufferedReader is = new BufferedReader(xover);

		String line;
		// Now read lines of text: the BufferedReader puts them in lines,
		// the InputStreamReader does Unicode conversion, and the
		// GZipInputStream "gunzip"s the data from the FileInputStream.
		while ((line = is.readLine()) != null) {
			System.out.println("Read: " + line);
		}
		is.close();
	}
}
// END main
