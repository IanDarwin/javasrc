package strings;

import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;

/** Potential difficulties of testing a class that insists
 * on writing to System.out. Well, we create a PrintStream
 * and replace System.out
 */
public class FmtTest {

	Fmt fmt;
	PrintStream out, oldOut;
	ByteArrayOutputStream outBytes;

	private void setupFiles(String data) throws Exception {
		ByteArrayInputStream ibs = new ByteArrayInputStream(data.getBytes());
		oldOut = System.out;
		outBytes = new ByteArrayOutputStream();
		out = new PrintStream(outBytes);
		System.setOut(out);
		fmt = new Fmt(ibs);
	}

	private void resetOut() {
		System.setOut(oldOut);
	}
	
	@Test
	public void testEmpty() throws Exception {
		setupFiles("");
		fmt.format();
		assertEquals("\n", outBytes.toString("UTF-8"));
		resetOut();
	}
	
	@Test
	public void testSmall() throws Exception {
		setupFiles("Once\nupon\na\ntime\n...");
		fmt.format();
		assertEquals("Once upon a time ... \n", outBytes.toString("UTF-8"));
		resetOut();
	}

	@Test
	public void testBlanks() throws Exception {
		setupFiles("Once\n\ntwice");
		fmt.format();
		assertEquals("Once \n\ntwice \n", outBytes.toString("UTF-8"));
		resetOut();
	}
	
	String longInput = 
		"Once, upon a midnight dreary, while I pondered," +
		"weak and weary, over many a quaint and curious" +
		"volume of forgotten lore, while I nodded, nearly"
		;
	String longExpected =
		"Once, upon a midnight dreary, while I pondered,weak and weary, over many \n" + 
		"a quaint and curiousvolume of forgotten lore, while I nodded, nearly \n"
		;
	
	@Test
	public void testLonger() throws Exception {
		setupFiles(longInput);
		fmt.format();
		assertEquals(longExpected, outBytes.toString("UTF-8"));
		resetOut();
	}
}
