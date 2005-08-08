import java.io.File;

/** Some things that are System Dependent.
 * All methods are static.
 * @author Ian Darwin
 * @version $Id$
 */
public class SysDep {

	/** Return the name of the Null device on platforms which support it,
	 * or "jnk" otherwise.
	 */
	public static String getDevNull() {

		final String UNIX_NULL_DEV = "/dev/null";
		if (new File(UNIX_NULL_DEV).exists()) {
			return UNIX_NULL_DEV;
		}

		String sys = System.getProperty("os.name");
		if (sys.startsWith("Windows")) {
			return "NUL:";
		}
		return "jnk";
	}
}
