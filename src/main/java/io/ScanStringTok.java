package io;

import java.io.*;
import java.util.*;

/**
 * ScanStringTok - show scanning a file with StringTokenizer.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ScanStringTok {
	protected LineNumberReader is;

	public static void main(String[] av) throws IOException {
		if (av.length == 0)
			new ScanStringTok(
				new InputStreamReader(System.in)).process();
		else 
			for (int i=0; i<av.length; i++)
				new ScanStringTok(av[i]).process();
	}

	/** Construct a file scanner by name */
	public ScanStringTok(String fileName) throws IOException {
		is = new LineNumberReader(new FileReader(fileName));
	}

	/** Construct a file scanner by existing Reader */
	public ScanStringTok(Reader rdr) throws IOException {
		// no point adding another level of buffering, if already
		// being buffered...
		if (rdr instanceof LineNumberReader)
			is = (LineNumberReader)rdr;
		else
			is = new LineNumberReader(rdr);
	}

	protected void process() {
		String s = null;
		try {
			while ((s = is.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, "@", true);
				String user = (String)st.nextElement();
				st.nextElement();
				String host = (String)st.nextElement();
				System.out.println("User name: " + user +
					"; host part: " + host);

				// Presumably you would now do something 
				// with the user and host parts...  

			}

		} catch (NoSuchElementException ix) {
			System.err.println("Line " + is.getLineNumber() +
				": Invalid input " + s);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
// END main
