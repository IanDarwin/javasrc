package io;

import java.io.*;

/**
 * Read a file by name and process a line at a time, the Java 8+ way,
 * using BufferedReader.lines().
 */
public class ReadLines {
	static int i = 0;

	public static void main(String[] args) throws IOException {
		new BufferedReader(new FileReader(args[0])).lines().forEach(s -> {
			System.out.println("Line: " + ++i + s);
		});
	}
}
