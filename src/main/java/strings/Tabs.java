/** Basic tab-character handling stuff.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Tabs {
	public final static int DEFTABSPACE =   8;	/* tabs every so often */
	protected int TABSPACE = DEFTABSPACE;
	public final static int MAXLINE  = 250;

	boolean tabstops[];		/* actual tab stops */

	public Tabs(int n) {
		tabstops = new boolean[MAXLINE];
		TABSPACE = n;
		settabs();
	}

	public Tabs() {
		tabstops = new boolean[MAXLINE];
		settabs();
	}

	/* settabs - set initial tab stops */
	public void settabs() {
		int i;
		/* note next line starts at one intentionally */
		for (i = 0; i < tabstops.length; i++) {
			tabstops[i] = 0 == (i % TABSPACE);
			Debug.println("settabs", "Tabs["+i+"]="+tabstops[i]);
		}
	}

	/* tabpos - returns true if col is a tab stop */
	boolean tabpos(int col) {
		/** If line too long, just put tabs whereever, don't throw exception */
		if (col > tabstops.length-1)
			return(true);
		else 
			return(tabstops[col]);
	}
}
