package io;

import java.io.*;

/**
 * Show PipedInputStream and PipedOutputStream
 */
public class PipedIO {
	public static void main(String[] args) throws Exception {

		PipedOutputStream pout = new PipedOutputStream();
		PipedInputStream pin = new PipedInputStream(pout);

		PrintWriter bout = new PrintWriter(pout);
		for (String str : lines) {
			System.out.println("Sending: " + str);
			bout.println(str);
		}
		bout.close(); // Ensure lines are sent into buffer.

		BufferedReader bin = new BufferedReader(new InputStreamReader(pin));
		String newStr;
		while ((newStr = bin.readLine()) != null) {
			System.out.println("Read back: " + newStr);
		}
	}

	static String[] lines = {
		"Hello World",
		"This is a drill",
		"Forescore and seven years ago, our parents brought forth.. a new nation!",
		"That's all, folks!",
	};
}

