import org.apache.regexp.*;

/**
 * Simple example of using RE class.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class RESimple {
	public static void main(String[] argv) throws RESyntaxException {
		String pattern = "^Q[^u]\\d+\\.";
		String input = "QA777. is the next flight. It is on time.";

		RE r = new RE(pattern); // Construct an RE object

		boolean found = r.match(input); // Use it to match an input.

		System.out.println(pattern +
			(found ? " matches " : " doesn't match ") + input);
	}
}
