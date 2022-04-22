package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Read a file by name and process a line at a time, the Java 8+ way,
 * using BufferedReader.lines().
 */
public class ReadFileIntoLinesStream {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String fileName = args[0];
		new BufferedReader(new FileReader(fileName)).lines().forEach(System.out::println);
	}
}
