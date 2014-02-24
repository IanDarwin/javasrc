package netweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/** A simple but reusable HTML/XML tag extractor; not to be confused with an XML parser.
 * @author Ian Darwin, Darwin Open Systems, www.darwinsys.com.
 */
public class ReadTag {

	/** The URL that this ReadTag object is reading */
	protected String myOrigin = null;
	/** The Reader for this object */
	protected BufferedReader inrdr = null;
	/** The set of tags we want to look for; null to return all tags */
	protected String[] wantedTags;
	
	public static final char XML_TAG_START = '<';
	private static final char XML_TAG_END = '>';
	private static final String XML_ENDTAG_LEADIN = "/";

	/** Construct a ReadTag given a URL String */
	public ReadTag(String theURLString) throws 
			IOException, MalformedURLException {

		this(new URL(theURLString));
	}

	/** Construct a ReadTag given a URL */
	public ReadTag(URL theURL) throws IOException {
		myOrigin = theURL.toString();
		// Open the URL for reading
		inrdr = new BufferedReader(new InputStreamReader(theURL.openStream()));
	}
	
	public ReadTag(Reader rdr) {
		myOrigin = "(pre-opened reader)";
		inrdr = new BufferedReader(rdr);
	}
	
	public ReadTag(InputStream is) {
		this(new InputStreamReader(is));
	}
	
	/**
	 * Allows you to specify a list of tags; only used if you call readTags(), not nextTag().
	 * @param tags The tags to set.
	 */
	public void setWantedTags(String[] tags) {
		this.wantedTags = tags.clone();
	}
	
	/**
	 * Accumulate the list of wanted tags one at a time in the least efficient way; for
	 * long lists you should use setWantedTags() instead.
	 * @param tagName
	 */
	public void addWantedTag(String tagName) {
		if (wantedTags == null) {
			wantedTags = new String[] { tagName };
		} else {
			String[] tempTags = new String[wantedTags.length + 1];
			System.arraycopy(wantedTags, 0, tempTags, 0, wantedTags.length);
			wantedTags[wantedTags.length-1] = tagName;
		}		
	}
	
	public List<Element> readTags() throws IOException {
		List<Element> tags = new ArrayList<Element>();
		Element aTag;
		while ((aTag = nextTag()) != null) {
			if (aTag.getType().startsWith(XML_ENDTAG_LEADIN)) // e.g., </html>
				continue;
			if (wantedTags == null) {
				tags.add(aTag);
			} else {
				for (int i = 0; i < wantedTags.length; i++) {
					if (aTag.getType().equalsIgnoreCase(wantedTags[i])) {
						tags.add(aTag);
						break;
					}
				}
			}
		}
		return tags;
	}
	
	private Element currentTag;
	
	/** Read the next tag.  */
	protected Element nextTag() throws IOException {
		int i;
		StringBuffer bodyText = new StringBuffer();
		while ((i = inrdr.read()) != -1) {
			char thisChar = (char)i;
			if (thisChar == XML_TAG_START) {
				if (currentTag != null && bodyText.length() > 0) {
					currentTag.setBodyText(bodyText.toString());
					bodyText.setLength(0);
				}
				Element tag = readTag();
				return currentTag = tag;
			} else {
				bodyText.append(thisChar);
			}
		}
		return null; // at EOF
	}

	/** Read one tag. 
	 * @author Ian Darwin
	 */
	protected Element readTag() throws IOException {
		StringBuffer tagType = new StringBuffer(XML_TAG_START);
		int i = XML_TAG_START;
	  
		while ((i = inrdr.read()) != -1 && i != XML_TAG_END && 				
				!Character.isWhitespace((char)i)) {
			
				tagType.append((char)i);
		}    

		Element tag = new Element(tagType.toString());
		if (i == XML_TAG_END) {
			return tag;		// no attributes
		}
		readAttributes(tag);
		return tag;
	}

	/** Read all the attributes for the current tag.
	 * @param tag
	 * @throws IOException
	 */
	private void readAttributes(final Element tag) throws IOException {
		final int S_INNAME = -1, S_EQUALS = '=', /*Q_NONE = 'N',*/ Q_SQUOTE = '\'', Q_DQUOTE = '"';
		final int S_INITIAL = S_INNAME;
		int i, state = S_INNAME;
		StringBuffer attrName = new StringBuffer(), attrValue = new StringBuffer();
		while ((i = inrdr.read()) != -1 && i != XML_TAG_END) {

			if (state == Q_SQUOTE && i != Q_SQUOTE) {
				attrValue.append((char)i);
			} else if (state == Q_DQUOTE && i != Q_DQUOTE) {
				attrValue.append((char)i);
			} else if (i == '=') {
				state = S_EQUALS;
			} else if (i == Q_SQUOTE) {
				if (state == Q_SQUOTE) {// End of quoted string
					setOneAttribute(tag, attrName, attrValue);
					state = S_INITIAL;
				} else 
					state = Q_SQUOTE;
			} else if (i == Q_DQUOTE) {
				if (state == Q_DQUOTE) {// End of quoted string
					setOneAttribute(tag, attrName, attrValue);
					state = S_INITIAL;
				} else 
					state = Q_DQUOTE;
			} else if (Character.isWhitespace((char)i)) {
				if (attrName.length() > 0) {
					setOneAttribute(tag, attrName, attrValue);
				}
				state = S_INITIAL;
			} else {
				StringBuffer whereToPutChars = state==S_INNAME ? attrName : attrValue;
				whereToPutChars.append((char)i);
			}
		}
		if (attrName.length() > 0) {
			setOneAttribute(tag, attrName, attrValue);
		}
	}

	/** Create one attribute in the map, converting name to lower case (since HTML tags
	 * are case-insensitive). XXX Consider moving this up to the caller to worry about.
	 * @param tag
	 * @param attrName
	 * @param attrValue
	 */
	private void setOneAttribute(Element tag, StringBuffer attrName, StringBuffer attrValue) {
		if (attrName.length() == 0 || !Character.isLetter(attrName.charAt(0))) {
			// System.err.println("warning: invalid attribute name: " + attrName);
			return;
		}
		tag.setAttribute(attrName.toString().toLowerCase(), attrValue.toString());
		attrName.setLength(0);
		attrValue.setLength(0);
	}

	public void close() throws IOException {
		inrdr.close();
	}


	/* Return a String representation of this object */
	public String toString() {
		return "ReadTag[" + myOrigin + "]";
	}


}
