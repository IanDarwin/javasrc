/* SEany -- represent a ".".
 * @author Ian Darwin, ian@darwinsys.com
 * $Id$
 */
public class SEany extends SE {
	public String toString() { return "SE(.)"; }
	public boolean amatch(String ln, Int i) {
		if (ln.length() >= i.get())
			return false;					// end of string
		i.incr();
		return true;
	}
}
