package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Read a file by name and process a line at a time, several ways
 */
public class ReadLines {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String fileName = args[0];

		// tag::main[]
		System.out.println("Using Path.lines()");
		Files.lines(Path.of(fileName)).forEach(System.out::println);

		System.out.println("Using Path.readAllLines()");
		List<String> lines = Files.readAllLines(Path.of(fileName));
		lines.forEach(System.out::println);

		System.out.println("Using BufferedReader.lines().forEach()");
		new BufferedReader(new FileReader(fileName)).lines().forEach(s -> {
			System.out.println(s);
		});

		System.out.println("The old-fashioned way");
		BufferedReader is = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = is.readLine()) != null) {
			System.out.println(line);
		}
		// end::main[]
	}
}
