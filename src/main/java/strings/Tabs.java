import com.darwinsys.util.Debug;

/** Basic tab-character handling stuff.
 * <p>
 * N.B. Can only handle equally-spaced tab stops as written.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class Tabs {
	/** tabs every so often */
	public final static int DEFTABSPACE =   8;
	/** the current tab stop setting. */
	protected int tabSpace = DEFTABSPACE;
	/** The longest line that we worry about tabs for. */
	public final static int MAXLINE  = 250;
	/** the current tab stops */
	protected boolean[] tabstops;

	/** Construct a Tabs object with a given tab stop settings */
	public Tabs(int n) {
		tabstops = new boolean[MAXLINE];
		tabSpace = n;
		settabs();
	}

	/** Construct a Tabs object with a default tab stop settings */
	public Tabs() {
		tabstops = new boolean[MAXLINE];
		settabs();
	}

	/** settabs - set initial tab stops */
	public void settabs() {
		int i;
		for (i = 0; i < tabstops.length; i++) {
			tabstops[i] = 0 == (i % tabSpace);
			Debug.println("settabs", "Tabs[" + i + "]=" + tabstops[i]);
		}
	}

	/** tabpos - returns true if given column is a tab stop.
	 * If current input line is too long, we just put tabs whereever, 
	 * no exception is thrown.
	 * @argument col - the current column number
	 */
	boolean tabpos(int col) {
		if (col > tabstops.length-1)
			return true;
		else 
			return tabstops[col];
	}
}
