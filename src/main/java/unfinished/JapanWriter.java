package unfinished;

import java.io.*;
public class JapanWriter {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(
			new OutputStreamWriter(System.out, "Cp939"));

		// Truncate characters by casting to byte (16-bit to 8-bit casting)
		char yen = '\u00a5';		// Japanese Yen
		char aeAcute = '\u01FC';	// Roman AE with acute accent
		out.println("Yen : " + yen);
		out.println("AE' : " + aeAcute);

		out.close();
	}
}

