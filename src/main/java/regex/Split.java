package regex;

import java.util.regex.*;

/** Split a String into a Java Array of Strings divided by an RE
 */
public class Split {
	private static final String SAMPLE_STRING = "the darwinian devonian explodian chicken";

	public static void main(String[] args) {
		String[] x = 
			Pattern.compile("ian").split(SAMPLE_STRING);
		for (int i=0; i<x.length; i++) {
			System.out.println(i + " \"" + x[i] + "\"");
		}
		for (String word : SAMPLE_STRING.split(" ")) {
			System.out.println(word);
		}
	}
}
