import java.io.*;
import java.util.regex.*;

/** Match lines against the pattern given on the command line.
 */
public class MatchLines {
	public static void main(String[] args) throws IOException {
		BufferedReader is =
			new BufferedReader(new InputStreamReader(System.in));
		if (args.length != 1) {
			System.err.println("Usage: MatchLines pattern");
			System.exit(1);
		}
		Pattern patt = Pattern.compile(args[0]);
		Matcher matcher = patt.matcher("");
		String line = null;
		while ((line = is.readLine()) != null) {
			matcher.reset(line);
			if (matcher.lookingAt()) {
				System.out.println("MATCH: " + line);
				/* ... */
			}
		}
	}
}
