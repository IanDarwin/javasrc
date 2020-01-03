package io;

import java.io.*;
import java.util.*;

/**
 * ScanStringTok - show scanning a file with StringTokenizer.
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
// tag::main[]
public class ScanStringTok {

	public static void main(String[] av) throws IOException {
		if (av.length == 0)
			System.err.printf("Usage: %s filename [...]%n",
				ScanStringTok.class.getSimpleName());
		else 
			for (int i=0; i<av.length; i++)
				process(av[i]);
	}

	static void process(String fileName) {
		String s = null;
		try (BufferedReader is = 
				new BufferedReader(new FileReader(fileName));) {
			while ((s = is.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, "@", true);
				String user = (String)st.nextElement();
				st.nextElement();
				String host = (String)st.nextElement();
				System.out.println("User name: " + user +
					"; host part: " + host);

				// Do something useful with the user and host parts...  
			}
		} catch (NoSuchElementException ix) {
			System.err.println("Malformed input " + s);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
// end::main[]
