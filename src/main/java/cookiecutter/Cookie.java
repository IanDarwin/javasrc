package cookiecutter;

// DO NOT PACKAGE INTO javax.servlet.http DUE TO INCOMPATIBILITIES

/** Represent the contents of one HTTP cookie. 
 * Since we want this usable by people without the Servlet API installed,
 * we generate our own implementation of Cookie for use in the
 * CookieCutter application. Similar to javax.servlet.http.Cookie,
 * but NOT plug-compatible due to extra full-function constructor and methods.
 */
public class Cookie implements Comparable<Cookie> {

	/** The host or domain which set the cookie */
	public String url;
	/** Canonicalized domainname for sort function */
	public String urlForSorting;
	/** The path to the page that set the cookie (often just "/") */
	public String path;
	/** The name, of name+value pair */
	public String name;
	/** The value, of name+value pair */
	public String value;
	public boolean fromJavaScript;
	/** True if set over a secure channel (SSL) */
	public boolean secure;
	/** When to expire. Arghh! This should have been a long! Y2038 ALERT */
	public int expDate;
	private String comment;

	/** Number of fields */
	public final static int WIDTH = 7;

	/* Constructor */
	public Cookie(String nam, String val,
		String dom, String path, 
		int date,
		boolean secure, boolean fromCl) {

		this(nam, val);
		setDomain(dom);
		fromJavaScript = fromCl;
		setPath(path);
		setSecure(secure);
		expDate = date;
	}

	/** Constructor with just name and value. */
	public Cookie(String n, String v) {
		name = n;
		value = v;
	}
	
	public Cookie() {
		// empty
	}

	int version = 0;
	public void setVersion(int vers) { version = vers; }
	public int getVersion() { return version; }

	public void setValue(String value) { this.value = value; }
	public String getValue() { return value; }

	public void setSecure(boolean secure) { this.secure = secure; }
	public boolean getSecure() { return isSecure(); }
	public boolean isSecure() { return secure; }

	public void setPath(String path) { this.path = path; }
	public String getPath() { return path; }

	public void setDomain(String host) {
		url = host;
		urlForSorting = url.startsWith(".") ? url.substring(1) : url;
	}
	public String getDomain() { return url; }

	public String getName() { return name; }

	public int getExpiry() { return expDate; }
	public void setExpiry(int when) { expDate = when; }

	/** Misnamed in original API. For compatibility only. Uses getExpiry.
	 * @deprecated
	 */

	public int getMaxAge() { return getExpiry(); }
	/** Misnamed in original API. For compatibility only. Uses setExpiry.
	 * @deprecated
	 */
	public void setMaxAge(int when) { setExpiry(when); }

	public String getComment() { 
		return comment;
	}

	public void setComment(String foo) { 
		this.comment = foo;
	}
	public boolean isClient() {
		return fromJavaScript;
	}

	public String toString() {
		return new StringBuffer(url).append("/").append(path).toString();
	}

	public int compareTo(Cookie c2) {
		return urlForSorting.compareTo(c2.urlForSorting);
	}
}
