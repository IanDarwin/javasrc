package strings;


/** Basic tab-character handling stuff.
 * <p>
 * N.B. Can only handle equally-spaced tab stops as written.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Tabs {
	/** tabs every so often */
	public final static int DEFTABSPACE =   8;
	/** the current tab stop setting. */
	protected int tabSpace = DEFTABSPACE;
	/** The longest line that we initially set tabs for. */
	public final static int MAXLINE  = 255;

	/** Construct a Tabs object with a given tab stop settings */
	public Tabs(int n) {
		if (n <= 0) {
			n = 1;
		}
		tabSpace = n;
	}

	/** Construct a Tabs object with a default tab stop settings */
	public Tabs() {
		this(DEFTABSPACE);
	}
	
	/**
	 * @return Returns the tabSpace.
	 */
	public int getTabSpacing() {
		return tabSpace;
	}
	
	/** isTabStop - returns true if given column is a tab stop.
	 * @param col - the current column number
	 */
	public boolean isTabStop(int col) {
		if (col <= 0)
			return false;
		return (col+1) % tabSpace == 0;
	}
}
// END main
