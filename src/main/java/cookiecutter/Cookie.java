/** Represent the contents of one cookie. 
 * Since we want this usable by people without the Servlet API installed,
 * we generate our own implementation of Cookie for use in the
 * CookieCutter application.
 */
public class Cookie implements Comparable
{
	public String url;
	public String urlForSorting;
	public String name;
	public String value;
	public boolean fromJavaScript;
	public String relURL;
	public boolean overSSL;
	public long expDate;

	/** Number of fields */
	public final static int WIDTH = 7;

	/* Constructor */
	public Cookie(String u, boolean fromCl, String rest, boolean overSec,
		long d, String n, String v) {
		url = u;
		urlForSorting = url.startsWith(".") ? url.substring(1) : url;
		fromJavaScript = fromCl;
		relURL = rest;
		overSSL = overSec;
		expDate = d;
		name = n;
		value = v;
	}

	public String toString() {
		return new StringBuffer(url).append(relURL).toString();
	}

	public int compareTo(Object o2) {
		Cookie c2 = (Cookie)o2;
		return urlForSorting.compareTo(c2.urlForSorting);
	}
}

