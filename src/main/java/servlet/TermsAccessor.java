package servlet;

import java.io.*;
import java.util.*;

/** This class provides access to the list of terms and definitions,
 * hiding the physical location and organization of the data.
 */
public class TermsAccessor {
	protected BufferedReader is;
	protected String ident;

	/** constructor that takes a FileName */
	public TermsAccessor(String inputFileName) throws IOException {
		is = new BufferedReader(new FileReader(inputFileName));
		String ident = is.readLine();
	}

	/** return the identifier string from the source */
	public String getIdent() {
		return ident;
	}

	/** This iterator() method simply defines an inner class that
	 * each time hasNext() succeeds, calling next() will
	 * a Term object, containing the term and definition.
	 */
	public Iterator iterator() {
		return new Iterator() {
			String line, term, defn;

			/** The hasNext() method returns true up until end of file. */
			public boolean hasNext() {
				try {
					return ((line = is.readLine()) != null);
				} catch (IOException e) {
					System.err.println("IO Error: " + e);
					return false;
				}
			}

			/** The next() method returns the next available Term object */
			public Object next() {
				int i;
				// loop, ignoring invalid lines.
				while ((i = line.indexOf("\t"))<0 && hasNext());
					;
				if (line == null)
					throw new IllegalStateException("Invalid EOF state");
				term = line.substring(0, i);
				defn = line.substring(i+1);
				return new Term(term, defn);
			}

			/** The remove method is not implemented: just throws. */
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
