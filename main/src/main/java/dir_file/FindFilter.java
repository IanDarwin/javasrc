package dir_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;


// tag::main[]
/** Class to encapsulate the filtration for Find.
 * For now just set*Filter() methods. Really needs to be a real
 * data structure (maybe LinkedList<FilterOp> or a Tree) for complex 
 * requests like:
 *	-n "*.html" -a \( -size < 0 -o mtime < 5 \).
 * XXX Consider replacing with BiPredicate and doing more parsing in Find.
 */
public class FindFilter {
	private enum SizeMode {GT, EQ, LT};
	SizeMode sizeMode;
	Find.Conjunction conj;
	long size;
	String name;
	Pattern nameRE;
	boolean debug = false;

	void setSizeFilter(String sizeFilter) {
		System.out.println("FindFilter.setSizeFilter()");
		sizeMode = SizeMode.EQ;
		char c = sizeFilter.charAt(0);
		if (c == '+') {
			sizeMode = SizeMode.GT;
			sizeFilter = sizeFilter.substring(1);
		} else {
			if (c == '-') {
				sizeMode = SizeMode.LT;
				sizeFilter = sizeFilter.substring(1);
			}
		}
		size = Long.parseLong(sizeFilter);
	}
	
	/** Add a conjunction */
	public void addConjunction(Find.Conjunction conj) {
		System.out.println("FindFilter.addConjunction()");
		if (this.conj != null) {
			throw new IllegalArgumentException(
				"Only one conjucntion allowed in this version");
		}
		this.conj = conj;
	}

	/** Convert the given shell wildcard pattern into internal form (an RE) */
	void setNameFilter(String nameToFilter) {
		nameRE = makeNameFilter(nameToFilter);
	}
	
	Pattern makeNameFilter(String name) {
		StringBuilder sb = new StringBuilder('^');
		for (char c : name.toCharArray()) {
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
		if (debug) {
			System.out.println("RE=\"" + sb + "\".");
		}
		// Should catch PatternException and rethrow for better diagnostics
		return Pattern.compile(sb.toString());
	}

	/** Do the filtering. For now, only filter on name, size or name+size */
	public boolean accept(Path p) throws IOException {
		if (debug) {
			System.out.println("FindFilter.accept(" + p + ")");
		}
		
		if (nameRE != null) {
			return nameRE.matcher(p.getFileName().toString()).matches();
		}

		// size handling.
		if (sizeMode != null) {
			long sz = Files.size(p);
			switch (sizeMode) {
			case EQ:
				return (sz == size);
			case GT:
				return (sz > size);
			case LT:
				return (sz < size);
			}
		}

		// Catchall
		return false;
	}
	
	public String getName() {
		return name;
	}
}
// end::main[]
