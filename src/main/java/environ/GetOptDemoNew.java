package environ;

import com.darwinsys.lang.GetOpt;
import com.darwinsys.lang.GetOptDesc;
import java.util.*;

/** Demonstrate the modern way of using GetOpt. This allows a subset of
 * <pre>UNIX sort options: sort -n -o outfile infile1 infile2</pre>
 * which means: sort numerically (-n), writing to file "outfile" (-o
 * outfile), sort from infile1 and infile2.
 */
// BEGIN main
public class GetOptDemoNew {
	public static void main(String[] argv) {
		boolean numeric_option = false;
		boolean errs = false;
		String outputFileName = null;

		GetOptDesc[] options = {
			new GetOptDesc('n', "numeric", false),
			new GetOptDesc('o', "output-file", true),
		};
		GetOpt parser = new GetOpt(options);
		Map<String,String> optionsFound = parser.parseArguments(argv);
		for (String key : optionsFound.keySet()) {
			char c = key.charAt(0);
			switch (c) {
				case 'n':
					numeric_option = true;
					break;
				case 'o':
					outputFileName = (String)optionsFound.get(key);
					break;
				case '?':
					errs = true;
					break;
				default:
					throw new IllegalStateException(
					"Unexpected option character: " + c);
			}
		}
		if (errs) {
			System.err.println("Usage: GetOptDemo [-n][-o file][file...]");
		}
		System.out.print("Options: ");
		System.out.print("Numeric: " + numeric_option + ' ');
		System.out.print("Output: " + outputFileName + "; ");
		System.out.print("Input files: ");
		for (String fileName : parser.getFilenameList()) {
			System.out.print(fileName);
			System.out.print(' ');
		}
		System.out.println();
	}
}
// END main
