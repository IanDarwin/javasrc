import java.io.*;
import java.net.*;

/** A simple HTML tag printer.
 * @author Ian Darwin, Darwin Open Systems, www.darwinsys.com.
 * @version $Id$
 */
public class ReadTag {
  
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Usage: ReadTag URL [...]");
			return;
		}
		ReadTag lc = new ReadTag();
		for (int i=0; i<args.length; i++) {
			lc.readURL(args[0]);
		}
	}
  
	/** Read a given URL, printing the tags.  */
	public void readURL(String theURLString) {
		URL theURL = null;
		BufferedReader inrdr = null;

		if (theURLString == null) {
			System.err.println("checkOut(null) isn't very useful");
			return;
		}

		// Open the URL for reading
		try {
			int i;
			theURL = new URL(theURLString);
			inrdr = new BufferedReader(new InputStreamReader(theURL.openStream()));

			while ((i = inrdr.read()) != -1) {
				char thisChar = (char)i;
				if (thisChar == '<') {
					String tag = readTag(inrdr);
					System.out.println(tag);
				}
			}
			inrdr.close();
		} catch (MalformedURLException e) {
			System.err.println("Can't parse " + theURLString);
			return;
		} catch (IOException e) {
			System.err.println("IO Error " + ":(" + e +")");
		}
	}

	/** Read one tag. Adapted from code by Elliotte Rusty Harold */
	public String readTag(BufferedReader is) throws IOException {
		StringBuffer theTag = new StringBuffer("<");
		int i = '<';
	  
		while (i != '>' && (i = is.read()) != -1) {
				theTag.append((char)i);
		}     
		return theTag.toString();
	}
}
