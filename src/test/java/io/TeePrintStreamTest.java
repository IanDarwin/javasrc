package io;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.darwinsys.io.FileIO;

public class TeePrintStreamTest {
	final String FILE_NAME = "err.log";

	@Test
	public void testOne() throws IOException {
		try (TeePrintStream ts = new TeePrintStream(System.err, FILE_NAME, true)) {
			System.setErr(ts);
			String MESSAGE = "An imitation error message";
			System.err.println(MESSAGE); // DO NOT REMOVE - is part of test!
			String message = FileIO.readAsString(FILE_NAME);
			assertEquals("read back string", MESSAGE, message);
		} finally {
			new File(FILE_NAME).delete();
		}
	}
}
