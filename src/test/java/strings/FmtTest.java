package strings;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
	final static String LINE_SEP =
		System.getProperty("os.name").indexOf("windows") != -1 ?
			"\r\n" :
			"\n";

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
		String[] string = outToStrings(outBytes.toString());
		assertEquals(1, string.length);
	}
	
	@Test
	public void testSmall() throws Exception {
		setupFiles("Once\nupon\na\ntime\n...");
		fmt.format();
		String[] expected = { "Once upon a time ... " };
		compareStringArrays(expected, 
				outToStrings(outBytes.toString()));
	}

	@Test
	public void testBlanks() throws Exception {
		setupFiles("Once\n\ntwice");
		fmt.format();
		String[] expected = { "Once ", "", "twice " };
		String[] actual = outToStrings(outBytes.toString());
		compareStringArrays(expected, actual);
	}
	
	String longInput = 
		// XXX DO NOT FIX THE SPACING here as it will change the line breaks.
		"Once, upon a midnight dreary, while I pondered," +
		"weak and weary, over many a quaint and curious" +
		"volume of forgotten lore, while I nodded, nearly"
		;

	String[] longExpected = {
		"Once, upon a midnight dreary, while I pondered,weak and weary, over many ", 
		"a quaint and curiousvolume of forgotten lore, while I nodded, nearly "
	};

	
	@Test
	public void testLonger() throws Exception {
		setupFiles(longInput);
		fmt.format();
		String[] outStrings = outToStrings(outBytes.toString());
		compareStringArrays(longExpected, outStrings);
	}

	private void compareStringArrays(String[] expected, String[] actual) {
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
	
	private String[] outToStrings(String s) throws Exception {
		List<String> strings = new ArrayList<>();
		try (BufferedReader is = new BufferedReader(new StringReader(s.toString()))) {
			String line;
			while ((line = is.readLine()) != null) {
				strings.add(line);
			}
		}
		return strings.toArray(new String[0]);
	}
}
