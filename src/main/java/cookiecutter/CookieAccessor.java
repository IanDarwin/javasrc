import java.io.*;
import java.util.*;

/** Input and Output module for CookieCutter.
 * Lines being read/written should look like this, tab separated:
 * host path local ssl expdt name value
 * For example:
 * www.fedex.com\tTRUE\t/\tFALSE\t1906484231\tFedEx_accrue\t120002424www4
 * See also: http://www.netscape.com/newsref/std/cookie_spec.html
 *
 * @author Ian F. Darwin
 * @version $Id$
 */
public class CookieAccessor {
	protected static String MAGIC = "# Netscape HTTP Cookie File";
	protected static String COMMENT = "#";

	public Vector read(String fileName) 
		throws FileNotFoundException, IOException {

		Vector ret = new Vector();

		BufferedReader is = new BufferedReader(new FileReader(fileName));

		if (!is.readLine().startsWith(MAGIC))
			die(fileName + " not a cookies file");
		String line;
		while ((line = is.readLine()) != null) {
			if (line.startsWith(COMMENT))
				continue;
			if (line.length() == 0)
				continue;
			StringTokenizer st = new StringTokenizer(line, "\t");
			int count = -1;
			if (st.countTokens() == Cookie.WIDTH) {
				count = Cookie.WIDTH;
			} else if (st.countTokens() == Cookie.WIDTH-1) {
				count = Cookie.WIDTH-1;
			} else {
				System.err.println("BAD INPUT: Line " + line + " has " + st.countTokens() + " tokens!");
				continue;
			}

			// The order that Netscape uses in this file is a wonder to behold.
			String domain = (String)st.nextToken();
			boolean clientSide = st.nextToken().equals("TRUE")?true:false;

			// Some slimeball sites use "" as the path. Correct on the fly.
			String path;
			if (count < Cookie.WIDTH)
				path = "/";
			else
				path = (String)st.nextToken();
			boolean secure = st.nextToken().equals("TRUE")?true:false;
			int expiry = Integer.parseInt(st.nextToken());
			String name = (String)st.nextToken();
			String value = (String)st.nextToken();

			Cookie c = new Cookie(
				name, value,
				domain, path,
				expiry,
				secure, clientSide);

			ret.addElement(c);
		}

		return ret;
	}

	/** Write the remaining cookies back to the file. Overwrites file.
	 */
	public void write(String fileName, Vector data) throws IOException {

		// If file exists, rename to .bak, deleting old .bak first.
		File f = new File(fileName);
		if (f.exists()) {
			File f2 = new File(fileName + ".bak");
			f2.delete();
			f.renameTo(f2);
		}
			
		PrintWriter is = new PrintWriter(new FileWriter(fileName));

		is.println(MAGIC);

		is.println(COMMENT + " Rewritten by the CookieCutter program");
		is.println();

		for (int i=0; i<data.size(); i++) {
			Cookie c = (Cookie) data.elementAt(i);
			is.println(c.getDomain() + "\t" +
				(c.isClient()?"TRUE":"FALSE") + "\t" +
				c.getPath() + "\t" +
				(c.isSecure()?"TRUE":"FALSE") + "\t" +
				c.getMaxAge() + "\t" +
				c.getName() + "\t" + c.getValue());
		}

		is.close();
	}

	protected void die(String s) {
		throw new IllegalArgumentException(s);
	}
}
