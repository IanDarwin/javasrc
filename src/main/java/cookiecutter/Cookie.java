/** Represent the contents of one cookie. */
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

	public Cookie(String u, boolean b1, String rest, boolean b2,
		long d, String n, String v) {
		url = u;
		urlForSorting = url.startsWith(".") ? url.substring(1) : url;
		fromJavaScript = b1;
		relURL = rest;
		overSSL = b2;
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

