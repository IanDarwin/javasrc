/* SEeol -- represent end-of-line "$"
 * @author Ian Darwin, ian@darwinsys.com
 * $Id$
 */
public class SEeol extends SE {
	public String toString() { return "EOL"; }
	public boolean amatch(String ln, Int i) {
		if (i.get() == ln.length()) {
			// no i.incr() since we don't use any chars in ln
			return true;
		}
		return false;
	}
}
