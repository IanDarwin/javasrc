package dir_file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


// BEGIN main
/** Class to encapsulate the filtration for Find.
 * For now just setTTTFilter() methods. Really needs to be a real
 * data structure to allow complex things like
 *	-n "*.html" -a \( -size < 0 -o mtime < 5 \).
 */
public class FindFilter implements FilenameFilter {
	boolean sizeSet;
	int size;
	String name;
	Pattern nameRE;
	boolean debug = false;

	void setSizeFilter(String sizeFilter) {
		size = Integer.parseInt(sizeFilter);
		sizeSet = true;
	}

	/** Convert the given shell wildcard pattern into internal form (an RE) */
	void setNameFilter(String nameFilter) {
		name = nameFilter;
		StringBuilder sb = new StringBuilder('^');
		for (char c : nameFilter.toCharArray()) {
			switch(c) {
				case '.':	sb.append("\\."); break;
				case '*':	sb.append(".*"); break;
				case '?':	sb.append('.'); break;
				// Some chars are special to RE and have to be escaped
				case '[':	sb.append("\\["); break;
				case ']':	sb.append("\\]"); break;
				case '(':	sb.append("\\("); break;
				case ')':	sb.append("\\)"); break;
				default:	sb.append(c); break;
			}
		}
		sb.append('$');
		if (debug)
			System.out.println("RE=\"" + sb + "\".");
		try {
			nameRE = Pattern.compile(sb.toString());
		} catch (PatternSyntaxException ex) {
			System.err.println("Error: RE " + sb.toString() +
				" didn't compile: " + ex);
		}
	}

	/** Do the filtering. For now, only filter on name */
	public boolean accept(File dir, String fileName) {
		File f = new File(dir, fileName);
		if (f.isDirectory()) {
			return true;	// allow recursion
		}

		if (nameRE != null) {
			return nameRE.matcher(fileName).matches();
		}

		// TODO size handling.

		// Catchall
		return false;
	}
	
	public String getName() {
		return name;
	}
}
// END main
