import java.io.*;
import java.net.*;

/** A simple but reusable HTML tag extractor.
 * @author Ian Darwin, Darwin Open Systems, www.darwinsys.com.
 * @version $Id$
 */
public class ReadTag {
	/** The URL that this ReadTag object is reading */
	protected URL theURL = null;
	/** The Reader for this object */
	protected BufferedReader inrdr = null;
  
	/* Simple main showing one way of using the ReadTag class. */
	public static void main(String[] args) throws MalformedURLException, IOException {
		if (args.length == 0) {
			System.err.println("Usage: ReadTag URL [...]");
			return;
		}

		for (int i=0; i<args.length; i++) {
			ReadTag rt = new ReadTag(args[0]);
			String tag;
			while ((tag = rt.nextTag()) != null) {
				System.out.println(tag);
			}
			rt.close();
		}
	}
  
	/** Constructor */
	public ReadTag(String theURLString) throws 
			IOException, MalformedURLException {

		if (theURLString == null) {
			throw new MalformedURLException("ReadTag(null) isn't very useful");
		}

		// Open the URL for reading
		theURL = new URL(theURLString);
		inrdr = new BufferedReader(new InputStreamReader(theURL.openStream()));
	}

	/** Read the next tag.  */
	public String nextTag() throws IOException {
		int i;
		while ((i = inrdr.read()) != -1) {
			char thisChar = (char)i;
			if (thisChar == '<') {
				String tag = readTag();
				return tag;
			}
		}
		return null;
	}

	public void close() throws IOException {
		inrdr.close();
	}

	/** Read one tag. Adapted from code by Elliotte Rusty Harold */
	protected String readTag() throws IOException {
		StringBuffer theTag = new StringBuffer("<");
		int i = '<';
	  
		while (i != '>' && (i = inrdr.read()) != -1) {
				theTag.append((char)i);
		}     
		return theTag.toString();
	}
}
