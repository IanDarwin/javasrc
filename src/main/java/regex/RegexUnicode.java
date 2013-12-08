package regex;

import java.util.regex.*;

/**
 * Explore the interactions of Regex and Unicode.
 * See the amazing charts at http://www.unicode.org/charts/.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class RegexUnicode {
	static class Data {
		String descr;
		String charStr;
		Data(String descr, String charStr) {
			this.descr = descr;
			this.charStr = charStr;
		}
	}
	static Data[] data = {
		new Data("ASCII A", "A"),
		new Data("Spanish Enye", "\u00d1"),
		new Data("Arabic", "\u0681"),
		new Data("Han", "\u3403"),
		new Data("Cyrillic", "\u04a0")
	};
	public static void main(String[] argv) {

		String patternPosixAlpha = "\\p{Alpha}";

		Pattern p = Pattern.compile(patternPosixAlpha);

		for (Data d : data) {

			Matcher m = p.matcher(d.charStr);

			boolean regexMatch = m.matches();
			char charForm = d.charStr.charAt(0);
			boolean isLetter = Character.isLetter(charForm);
			System.out.printf(
				"%s (%c): isLetter %b, regex %b%n", 
				d.descr, charForm,
				isLetter, regexMatch);
		}
		// From which we can conclude that posix
		// "Alpha" is only defined for USASCII.
	}
}
