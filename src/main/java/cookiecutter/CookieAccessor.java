import java.io.*;
import java.util.*;

public class CookieIO {
	protected static String MAGIC = "# Netscape HTTP Cookie File";
	protected static String COMMENT = "#";

	public Vector read(String file) 
		throws FileNotFoundException, IOException { 

		Vector ret = new Vector();

		BufferedReader is = new BufferedReader(new FileReader(file));

		if (!is.readLine().startsWith(MAGIC))
			die(file + " not a cookies file");
		String line;
		while ((line = is.readLine()) != null) {
			if (line.startsWith(COMMENT))
				continue;
			if (line.length() == 0)
				continue;
			StringTokenizer st = new StringTokenizer(line, "\t");
			if (st.countTokens() != Cookie.WIDTH) {
				System.err.println("BAD INPUT: Line " + line + " has " + st.countTokens() + " tokens!");
				continue;
			}
			Cookie c = new Cookie(
				(String)st.nextToken(),
				st.nextToken().equals("TRUE")?true:false,
				(String)st.nextToken(),
				st.nextToken().equals("TRUE")?true:false,
				Long.parseLong(st.nextToken()),
				(String)st.nextToken(),
				(String)st.nextToken());
			ret.addElement(c);
		}

		return ret;
	}

	protected void die(String s) {
		throw new IllegalArgumentException(s);
	}
	public void write(String file, Vector data) /* throws IOException */ {
		throw new IllegalArgumentException("writing code not written yet");
	}
}
