package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * REsubstr -- demonstrate RE Match -> String.substring()
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class REsubstr {
	public static void main(String[] argv) {
		// tag::main[]
		String patt = "Q[^u]\\d+\\.";
		Pattern r = Pattern.compile(patt);
		String line = "Order QT300. Now!";
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println(patt + " matches \"" +
				line.substring(m.start(0), m.end(0)) +
				"\" in \"" + line + "\"");
		} else {
			System.out.println("NO MATCH");
		}
		// end::main[]
	}
}
