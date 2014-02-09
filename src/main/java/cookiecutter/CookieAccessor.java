package cookiecutter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** Input and Output module for CookieCutter.
 * Lines being read/written should look like this, tab separated:
 * host path local ssl expdt name value
 * For example:
 * www.fedex.com\tTRUE\t/\tFALSE\t1906484231\tFedEx_accrue\t120002424www4
 * See also: http://www.netscape.com/newsref/std/cookie_spec.html
 *
 * @author Ian F. Darwin
 */
public class CookieAccessor {
	protected static final String MAGIC = "# Netscape HTTP Cookie File";
	protected static final String COMMENT = "#";

	public List<Cookie> read(String fileName) 
		throws FileNotFoundException, IOException {
		Reader is = new FileReader(fileName);
		
		return read(is, fileName);
	}
	
	public List<Cookie> read(Reader rdr, String fileName) throws IOException {
		List<Cookie> ret = new ArrayList<Cookie>();

		BufferedReader is = new BufferedReader(rdr);
		String line;
		if ((line = is.readLine()) == null || !line.startsWith(MAGIC))
			die(fileName + " not a cookies file");

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

			// The order that Netscape uses in the columns of this file is a wonder to behold.
			String domain = (String)st.nextToken();
			boolean clientSide = st.nextToken().equals("TRUE");

			// Some slimeball sites use "" as the path. Correct on the fly.
			String path;
			if (count < Cookie.WIDTH)
				path = "/";
			else
				path = (String)st.nextToken();
			boolean secure = st.nextToken().equals("TRUE");
			int expiry = Integer.parseInt(st.nextToken());
			String name = (String)st.nextToken();
			String value = (String)st.nextToken();

			Cookie c = new Cookie(
				name, value,
				domain, path,
				expiry,
				secure, clientSide);

			ret.add(c);
		}
		is.close();
		return ret;
	}

	/** Write the remaining cookies back to the file. Overwrites file.
	 */
	public void write(String fileName, List<Cookie> data) throws IOException {

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

		for (Cookie c : data) {
			is.println(c.getDomain() + "\t" +
				(c.isClient()?"TRUE":"FALSE") + "\t" +
				c.getPath() + "\t" +
				(c.isSecure()?"TRUE":"FALSE") + "\t" +
				c.getExpiry() + "\t" +
				c.getName() + "\t" + c.getValue());
		}

		is.close();
	}

	protected void die(String s) {
		throw new IllegalArgumentException(s);
	}
}
