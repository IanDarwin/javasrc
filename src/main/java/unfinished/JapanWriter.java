package unfinished;

import java.io.*;
public class JapanWriter {
	public static void main(String[] args) throws UnsupportedEncodingException {
		PrintWriter out = new PrintWriter(
			new OutputStreamWriter(System.out, "Cp939"));

		char yen = '\u00a5';		// Japanese Yen
		char aeAcute = '\u01FC';	// Roman AE with acute accent
		out.println("Yen : " + yen);
		out.println("AE' : " + aeAcute);

		out.close();
	}
}

