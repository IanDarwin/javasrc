import java.util.regex.*;

/**
 * Quick demo of RE substitution: correct "demon" and other
 * spelling variants to the correct, non-satanic "daemon".
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class ReplaceDemo {
	public static void main(String[] argv) {

		// Make an RE pattern to match almost any form (deamon, demon, etc.).
		String patt = "d[ae]{1,2}mon";	// i.e., 1 or 2 'a' or 'e' any combo

		// A test input.
		String input = "Unix hath demons and deamons in it!";
		System.out.println("Input: " + input);

		// Run it from a RE instance and see that it works
		Pattern r = Pattern.compile(patt);
		Matcher m = r.matcher(input);
		System.out.println("ReplaceAll: " + m.replaceAll("daemon"));

		// Show the appendReplacement method
		m.reset();
		StringBuffer sb = new StringBuffer();
		System.out.print("Append methods: ");
		while (m.find()) {
			m.appendReplacement(sb, "daemon");	// Copy to before first match,
												// plus the word "daemon"
		}
		m.appendTail(sb);						// copy remainder
		System.out.println(sb.toString());
	}
}
