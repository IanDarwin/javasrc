import java.io.*;
import org.apache.regexp.*;

/** Class to encapsulate the filtration for Find */
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
		for (int i = 0; i < nameFilter.length; i++) {
			char c = nameFilter.charAt(i);
			switch(c) {
				case '.':	sb.append("\\."); break;
				case '*':	sb.append(".*"); break;
				case '?':	sb.append('.'); break;
				default:	sb.append(c); break;
			}
			sb.append('$');
		}
		try {
			nameRE = new RE(sb.toString());
		} catch (RESyntaxException ex) {
			System.err.println("For shame! " + ex);
		}
	}

	/** Do the filtering. For now, only filter on name */
	public boolean accept(File dir, String fileName) {
		if (name != null) {
			return nameRE.match(fileName);
		}

		// Catchall
		return false;
	}
}
