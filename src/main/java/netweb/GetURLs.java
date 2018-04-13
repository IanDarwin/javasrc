package netweb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
	final static String[] wantTags = {
		"<a ", "<A ",
		"<img ", "<IMG ",
		"<frame ", "<FRAME ",
	};

	public void close() throws IOException {
		if (reader != null) 
			reader.close();
	}
	
	/**
	 * When called as a main program, extract and print the urls
	 * @param argv Command line args
	 * @throws MalformedURLException If not a valid URL
	 * @throws IOException If error reading.
	 */
	public static void main(String[] argv) throws 
			MalformedURLException, IOException {
		String theURL = argv.length == 0 ?
			"http://localhost:8080/" : argv[0];
		GetURLs gu = new GetURLs(theURL);
		// Tell the reader we only want link tags
		gu.reader.setWantedTags(GetURLs.wantTags);
		List<Element> urls = gu.reader.readTags();
		for (Element url : urls) {
			System.out.println(url);
		}
	}
}
