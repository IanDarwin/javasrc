package io;

import java.io.*;

/**
 * Read a file by name and process a line at a time, the Java 8+ way,
 * using BufferedReader.lines().
 */
public class ReadFileIntoLinesStream {
	public static void main(String[] args) throws IOException {
		String fileName = args[0];
		new BufferedReader(new FileReader(fileName)).lines().forEach(System.out::println);
	}
}
