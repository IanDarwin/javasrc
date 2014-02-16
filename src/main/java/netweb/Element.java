package netweb;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An XML element has a name (which in this version will include the ns prefix if any), 
 * and zero or more attributes.
 */
public class Element {
	
	/** The type of element, e.g., in HTML, "a", "img", etc. */
	private final String type;	
	/** The attributes */
	private Map<String,String> attributes = new HashMap<String,String>();
	/** The body text, if any, or null */
	private String bodyText;
	
	/**
	 * @param type
	 */
	public Element(String type) {
		super();
		this.type = type;
	}
	/**
	 * @return Returns the attributes.
	 */
	public String getAttribute(String key) {
		return (String)attributes.get(key);
	}
	/**
	 * @param attributes The attributes to set.
	 */
	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}
	
	public Set<String> keySet() {
		return attributes.keySet();
	}
	
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	public String getBodyText() {
		return bodyText;
	}
	
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	
	/** Generate a strng representation of this Element.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append('<').append(type);
		Set<String> keyset = attributes.keySet();
		for (String name : keyset) {
			String value = (String) attributes.get(name);
			sb.append(' ').append(name).append('=').append(value);
		}

		if (bodyText == null ) {
			sb.append('/').append('>');
		} else {
			sb.append('>').append(bodyText).append('<').append("/").append(type).append('>');
		}
		return sb.toString();
	}
}
