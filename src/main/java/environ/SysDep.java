/** Some things that are System Dependent.
 * All methods are static, like java.lang.Math.
 * @author Ian Darwin
 * @version $Id$
 */
public class SysDep {
	/** Return the name of the Null device on platforms which support it,
	 * or "jnk" otherwise.
	 */
	public static String getDevNull() {
		String sys = System.getProperty("os.name");
		if (sys==null || sys.indexOf("Mac") >= 0)
			return "jnk";
		if (sys.startsWith("Windows"))
			return "NUL:";
		return "/dev/null";
	}
}
