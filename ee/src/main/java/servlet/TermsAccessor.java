package servlet;

import java.io.*;
import java.util.*;

/** This class provides access to the list of terms and definitions,
 * hiding the physical location and organization of the data.
 */
public class TermsAccessor {
	protected BufferedReader is;
	protected String ident;
	private List<Term> definitions = new ArrayList<Term>();

	/** constructor that takes a FileName */
	public TermsAccessor(String inputFileName) throws IOException {
		is = new BufferedReader(new FileReader(inputFileName));
		doReading();
	}
	
	public TermsAccessor(Reader rdr) throws IOException {
		is = new BufferedReader(rdr);
		doReading();
	}

	/**
	 * @throws IOException
	 */
	private void doReading() throws IOException {
		ident = is.readLine();
		String line;
		while ((line = is.readLine()) != null) {
			if (line.length() == 0 || line.startsWith("#"))
				continue;
			int i = line.indexOf("\t");
			if (i == -1) {
				throw new IllegalArgumentException("invalid line " + line);
			}
			String term = line.substring(0, i);
			String defn = line.substring(i+1);
			definitions.add(new Term(term, defn));
		}
	}

	/** return the identifier string from the source */
	public String getIdent() {
		return ident;
	}

	/** This iterator() method simply defines an inner class that
	 * each time hasNext() succeeds, calling next() will
	 * a Term object, containing the term and definition.
	 */
	public Iterator<Term> iterator() {
		return definitions.iterator();
	}
}
