import org.apache.regexp.*;

/**
 * Example of using RE `split' method.
 * Remember that `split' makes you specify the boundaries; not
 * the patterns you want to extract but what's between them.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Split {
	public static void main(String[] argv) throws RESyntaxException {
		String pattern = "\\W+";	// non-word chars will be the delimiters.
		String input = "QA777. is the next flight. It is on time.";

		RE r = new RE(pattern); // Construct an RE object

		String[] words = r.split(input);

		for (int i = 0; i < words.length; i++) {
			System.out.println(i + " " + words[i]);
		}
	}
}
