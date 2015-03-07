package database;

// package jabadot;


/** The main "properties"-like class for configuring JabaDot.
 * DUMMY VERSION - no longer functional, here so other stuff compiles.
 */
public class JDConstants  {

	/** This must be a full path, since ya gotta start someplace. */
	static String JABADOT_DIR;

	/** Get a property, but substitute $DIR with JABADOT_DIR */
	public static String getProperty(String key) {
		return key;
	}

	/** Replace one string in another
	 * @return The modified string, or the original if no change made.
	 */
	public static String replace(String oldStr, String newStr, String inString) {
		int start = inString.indexOf(oldStr);
		if (start == -1) {
			return inString;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(inString.substring(0, start));
		sb.append(newStr);
		sb.append(inString.substring(start+oldStr.length()));
		return sb.toString();
	}

	public static String getJABADOT_DIR() {
		return JABADOT_DIR;
	}

	public static void setJabadotDir(String jabadot_dir) {
		JABADOT_DIR = jabadot_dir;
	}
}
