package io;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.darwinsys.io.FileIO;

public class TeePrintStreamTest {

	@Test
	public void testOne() throws IOException {
		try {
			TeePrintStream ts = new TeePrintStream(System.err, "err.log", true);
			System.setErr(ts);
			String MESSAGE = "An imitation error message";
			System.err.print(MESSAGE);
			ts.close();
			String message = FileIO.readAsString("err.log");
			assertEquals("read back string", MESSAGE, message);
		} finally {
			new File("err.log").delete();
		}
	}
}
