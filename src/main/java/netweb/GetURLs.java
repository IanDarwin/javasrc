import java.io.*;
import java.net.*;
import java.util.*;

public class GetURLs {
	/** The tag reader */
	ReadTag reader;

	public GetURLs(URL theURL) throws IOException {
		reader = new ReadTag(theURL);
	}

	public GetURLs(String theURL) throws MalformedURLException, IOException {
		reader = new ReadTag(theURL);
	}

	/* The tags we want to look at */
	public final static String[] wantTags = {
		"<a ", "<A ",
		"<applet ", "<APPLET ",
		"<img ", "<IMG ",
		"<frame ", "<FRAME ",
	};

	public ArrayList getURLs() throws IOException {
		ArrayList al = new ArrayList();
		String tag;
		while ((tag = reader.nextTag()) != null) {
			for (int i=0; i<wantTags.length; i++) {
				if (tag.startsWith(wantTags[i])) {
					al.add(tag);
					continue;		// optimization
				}
			}
		}
		return al;
	}

	public void close() throws IOException {
		if (reader != null) 
			reader.close();
	}
	public static void main(String[] argv) throws 
			MalformedURLException, IOException {
		String theURL = argv.length == 0 ?
			"http://localhost/" : argv[0];
		GetURLs gu = new GetURLs(theURL);
		ArrayList urls = gu.getURLs();
		Iterator urlIterator = urls.iterator();
		while (urlIterator.hasNext()) {
			System.out.println(urlIterator.next());
		}
	}
}
