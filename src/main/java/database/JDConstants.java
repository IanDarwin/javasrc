package database;

// package jabadot;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/** The main "properties"-like class for configuring JabaDot.
 * All of its properties starting with "jabadot.dir" are loaded from
 * Initialization Parameters.
 * <p>
 * This program has been configured as a "servlet" -
 * which it really is not -- just so it can get the value for
 * JABADOT_DIR from the servlet parameters. As a result, this
 * file should be listed as "preload" in the servlet engine configuration.
 *
 * @version $Id$
 */
public class JDConstants extends HttpServlet {

	/** This must be a full path, since ya gotta start someplace. */
	protected static String JABADOT_DIR;

	protected static ServletContext ctx;

	/** This init servlet method loads the Properties. We know we'll
	 * get called once at the beginning of time, since we're set up
	 * that way in web.xml (as a pre-loaded servlet).
	 */
	public void init() throws ServletException {

		ctx = getServletConfig().getServletContext();

		// Get the JABADOT_DIR before all else!

		// Be careful of the difference between 
		// ServletContext.getInitParameter and Servlet.getInitParameter!
		// Be sure the installer puts in in jabadot/WEB-INF/web.xml,
		// NOT in tomcat/conf/web.xml - it will be ignored there!!

		JABADOT_DIR = ctx.getInitParameter("jabadot.dir");
		if (JABADOT_DIR == null) {
			System.out.println(
				"+------------- ERROR --------------------");
			System.out.println(
				"jabadot.dir not set as a context parameter.");
			System.out.println(
				"Please RTFM and try again"); // Time to WTFM!
			System.out.println(
				"+----------------------------------------");
		} else {
			System.out.println("JABADOT_DIR " + JABADOT_DIR);
		}

	}

	/** Get a property, but substitute $DIR with JABADOT_DIR */
	public static String getProperty(String key) {
		if (ctx==null)
			throw new IllegalStateException(
				"JDConstants.getProperty: not initialized!");
		return lookup(key);
	}

	private static String lookup(String key) {
		String val = ctx.getInitParameter(key);
		if (val == null)
			return null;
		return replace("$DIR", JABADOT_DIR, val);
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
}
