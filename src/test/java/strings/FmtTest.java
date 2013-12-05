package strings;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.io.StringReader;

import org.junit.Test;

/** Potential difficulties of testing a class that insists
 * on writing to System.out. Instead of mucking about with
 * System.out here, we refactored the class under test to accept
 * a PrintWriter in some of its constructors.
 */
public class FmtTest {

	Fmt fmt;
	PrintWriter out;
	CharArrayWriter outBytes;

	private void setupFiles(String data) throws Exception {
		BufferedReader ibs = new BufferedReader(new StringReader(data));
		outBytes = new CharArrayWriter();
		out = new PrintWriter(outBytes);
		fmt = new Fmt(ibs, out);
	}
	
	@Test
	public void testEmpty() throws Exception {
		setupFiles("");
		fmt.format();
		assertEquals("\n", outBytes.toString());
	}
	
	@Test
	public void testSmall() throws Exception {
		setupFiles("Once\nupon\na\ntime\n...");
		fmt.format();
		assertEquals("Once upon a time ... \n", outBytes.toString());
	}

	@Test
	public void testBlanks() throws Exception {
		setupFiles("Once\n\ntwice");
		fmt.format();
		assertEquals("Once \n\ntwice \n", outBytes.toString());
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
		assertEquals(longExpected, outBytes.toString());
	}
}
