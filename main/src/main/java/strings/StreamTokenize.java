package strings;

import java.util.stream.IntStream;

/** 
 * Show using a String.split and a Stream
 * @author Daniel Hinojosa
 */
// tag::main[]
public class StreamTokenize {
	public final static int MAXFIELDS = 5;
	public final static String DELIM = "|";

	public static String[] process(String line) {
		var tokens = line.split(DELIM);
		return IntStream
			.range(0, MAXFIELDS)
			.boxed()
			.map(i -> convertArrayIndexToNull(tokens, i))
			.toArray(String[]::new);
	}

	private static String convertArrayIndexToNull(String[] list, int index) {
		try {
			var item = list[index];
			if (item.isEmpty()) return null;
			return item;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static void printResults(String input, String[] outputs) {
		System.out.println("Input: " + input);
		for (String s : outputs)
			System.out.println("Output " + s + " was: " + s);
	}

	public static void main(String[] a) {
		printResults("A|B|C|D", process("A|B|C|D"));
		printResults("A||C|D", process("A||C|D"));
		printResults("A|||D|E", process("A|||D|E"));
	}
}
// end::main[]
