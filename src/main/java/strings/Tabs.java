package strings;

import com.darwinsys.util.Debug;

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
	/** the current tab stops */
	protected boolean[] tabstops;

	/** Construct a Tabs object with a given tab stop settings */
	public Tabs(int n) {
		if (n <= 0) {
			n = 1;
		}
		tabstops = new boolean[MAXLINE];
		tabSpace = n;
		settabs();
	}

	/** Construct a Tabs object with a default tab stop settings */
	public Tabs() {
		this(DEFTABSPACE);
	}

	/** settabs - set initial tab stops */
	private void settabs() {
		for (int i = 0; i < tabstops.length; i++) {
			tabstops[i] = ((i+1) % tabSpace) == 0;
			Debug.println("tabs", "Tabs[" + i + "]=" + tabstops[i]);
		}
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
		if (col > tabstops.length - 1) {
			tabstops = new boolean[tabstops.length * 2];
			settabs();
		}
		return tabstops[col];
	}
}
// END main
