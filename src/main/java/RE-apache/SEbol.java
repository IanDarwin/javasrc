/* SEbol - represent the beginning of line "^".
 * @author Ian Darwin, ian@darwinsys.com
 * $Id$
 */
public class SEbol extends SE {
	public String toString() { return "SE(^)"; }
	public boolean amatch(String ln, Int i) {
		if (i.get()>0)
			return false;
		// no i.incr() since we don't use any chars in ln
		return true;
	}
}
