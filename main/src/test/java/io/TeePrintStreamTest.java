package io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class TeePrintStreamTest {
	final String FILE_NAME = "err.log";

	@Test
	void one() throws IOException {
		try (TeePrintStream ts = new TeePrintStream(System.err, FILE_NAME, true)) {
			System.setErr(ts);
			String MESSAGE = "An imitation error message";
			System.err.println(MESSAGE); // DO NOT REMOVE - is part of test!
			try (BufferedReader is = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
				String message = is.readLine();
				assertEquals(MESSAGE, message, "read back string");
			}
		} finally {
			new File(FILE_NAME).delete();
		}
	}
}
