package email;

/** Represent a Mail or News Header */
public class Header {
	public String type;
	public String text;
	Header(String n, String v) {
		type = n;
		text = v;
	}
	public String toString() {
		return new StringBuffer("Header[").append(type).append(": ").append(text).append("]").toString();
	}
}
