/* SEeol -- represent end-of-line "$"
 * @author Ian Darwin, ian@darwinsys.com
 * $Id$
 */
public class SEeol extends SE {
	public String toString() { return "SE($)"; }
	public boolean amatch(String ln, Int i) {
		if (i.get() == ln.length() - 1) {
			// no i.incr() since we don't use any chars in ln
			return true;
		}
		return false;
	}
}
