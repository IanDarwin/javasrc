import java.net.*;
import java.io.*;
import java.util.*;

/** Get list of APPLET tags in one HTML file.
 *
 * @author	Ian Darwin, Darwin Open Systems, www.darwinsys.com.
 * Routine "readTag" shamelessly stolen from
 * Elliott Rusty Harold's "ImageSizer" program.
 */
public class AppletFinder {

	public static void main(String[] av) {
		String[] tags = new AppletFinder().doPage(av[0]);
		for (int i=0;i<tags.length; i++)
			System.out.println("APPLET: " +i+" " + tags[i]);
	}

	public String[] doPage(String pageURL) {
		String result[] = null;
		URL root;

		if (pageURL != null) {
			//Open the URL for reading
			try {
				if (pageURL.indexOf(":") != -1) {
					root = new URL(pageURL);
				}
				else {
					root = new URL("http://" + pageURL);
				}
				result = findApplets(root);
			}
			catch (MalformedURLException e) {
				System.err.println(pageURL + 
					" is not a parseable URL");
				System.err.println(e);
			}
		}
		return result;
	}

	public String[] findApplets(URL u) {
		BufferedReader inrdr = null;
		Vector v = new Vector();
		char thisChar = 0;
	  
		try {
			inrdr = new BufferedReader(new InputStreamReader(u.openStream()));
			int i;
			while ((i = inrdr.read()) != -1) {
				thisChar = (char)i;
				if (thisChar == '<') {
					String tag = readTag(inrdr);
					// System.out.println("TAG: " + tag);
					if (tag.toUpperCase().startsWith("<APPLET"))
						v.addElement(tag);
				}
			}
			inrdr.close();
		}
		catch (IOException e) {
			System.err.println("Error reading from main URL: " + e);
		}
		String applets[] = new String[v.size()];
		v.copyInto(applets);
		return applets;
	}
 
	public String readTag(BufferedReader is) {
    StringBuffer theTag = new StringBuffer("<");
    char theChar = '<';
  
    try {
       while (theChar != '>' && ((theChar = (char)is.read()) != -1)) {
         theTag.append(theChar);
       } // end while
     }  // end try
     catch (IOException e) {
        System.err.println(e);
     }     

     return theTag.toString();
  }
}
