package io;

import java.io.*;

/** Demonstrate creating readers and writers with a specific encoding.
 */
public class UseConverters {
	public static void main(String[] args) {
		try {
			BufferedReader fromKanji = new BufferedReader(
				new InputStreamReader(
					new FileInputStream("kanji.txt"), "EUC_JP"));
			PrintWriter toSwedish = new PrintWriter(
				new OutputStreamWriter(			// XXX check enco
					new FileOutputStream("sverige.txt"), "ISO8859_3"));

			// reading and writing here...
			String line = fromKanji.readLine();
			System.out.println("-->" + line + "<--");
			toSwedish.println(line);
			fromKanji.close();
			toSwedish.close();
		} catch (UnsupportedEncodingException exc) {
			System.err.println("Bad encoding" + exc);
			return;
		} catch (IOException err) {
			System.err.println("I/O Error: " + err);
			return;
		}
	}
}
