import java.io.*;
import org.apache.regexp.*;
import com.darwinsys.util.Debug;

/** Class to encapsulate the filtration for Find.
 * For now just setTTTFilter() methods. Really needs to be a real
 * data structure to allow complex things like
 *		-n "*.html" -a \( -size < 0 -o mtime < 5 \).
 * @version $Id$
 */
public class FindFilter implements FilenameFilter {
	boolean sizeSet;
	int size;
	String name;
	RE nameRE;

	public FindFilter() {
	}

	void setSizeFilter(String sizeFilter) {
		size = Integer.parseInt(sizeFilter);
		sizeSet = true;
	}

	/** Convert the given shell wildcard pattern into internal form (an RE) */
	void setNameFilter(String nameFilter) {
		name = nameFilter;
		StringBuffer sb = new StringBuffer('^');
		for (int i = 0; i < nameFilter.length(); i++) {
			char c = nameFilter.charAt(i);
			switch(c) {
				case '.':	sb.append("\\."); break;
				case '*':	sb.append(".*"); break;
				case '?':	sb.append('.'); break;
				default:	sb.append(c); break;
			}
		}
		sb.append('$');
		Debug.println("name", "RE=\"" + sb + "\".");
		try {
			nameRE = new RE(sb.toString());
		} catch (RESyntaxException ex) {
			System.err.println("For shame! " + ex);
		}
	}

	/** Do the filtering. For now, only filter on name */
	public boolean accept(File dir, String fileName) {
		File f = new File(dir, fileName);
		if (f.isDirectory()) {
			return true;	// allow recursion
		}

		if (name != null) {
			return nameRE.match(fileName);
		}

		// TODO size handling.

		// Catchall
		return false;
	}
}
