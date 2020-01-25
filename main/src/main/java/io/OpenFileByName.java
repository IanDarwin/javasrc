package io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class OpenFileByName {
	final static String INPUT_FILE_NAME = "lines.txt";
	final static String OUTPUT_FILE_NAME = "bytes.dat";

	public static void main(String[] args) throws IOException {
		newWay();
		oldWay();
		oldWayShorter();
	}

	// tag::main[]
	static void newWay() throws IOException {
		Files.lines(Path.of(INPUT_FILE_NAME)).forEach(System.out::println);
	}

	static void oldWay() throws IOException {
		BufferedReader is =
			new BufferedReader(new FileReader(INPUT_FILE_NAME));
		BufferedOutputStream bytesOut = new BufferedOutputStream(
			new FileOutputStream(OUTPUT_FILE_NAME));

		// Read from is, write to bytesOut
		String line;
		while ((line = is.readLine()) != null) {
			line = doSomeProcessingOn(line);
			bytesOut.write(line.getBytes(StandardCharsets.UTF_8));
			bytesOut.write('\n');
		}

		bytesOut.close();
		is.close();
	}
	// end::main[]

	// tag::try-with-resource[]
	static void oldWayShorter() throws IOException {
		try (BufferedReader is = 
			new BufferedReader(new FileReader(INPUT_FILE_NAME));
			BufferedOutputStream bytesOut = new BufferedOutputStream(
				new FileOutputStream(OUTPUT_FILE_NAME.replace("\\.", "-1.")));) {

			// Read from is, write to bytesOut
			String line;
			while ((line = is.readLine()) != null) {
				line = doSomeProcessingOn(line);
				bytesOut.write(line.getBytes("UTF-8"));
				bytesOut.write('\n');
			}

		}
	}
	// end::try-with-resource[]

	static String doSomeProcessingOn(String line) {
		return line;
	}
}
