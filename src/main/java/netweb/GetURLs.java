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
		"<applet ", "<APPLET ",
		"<img ", "<IMG ",
		"<frame ", "<FRAME ",
	};

	public void close() throws IOException {
		if (reader != null) 
			reader.close();
	}
	public static void main(String[] argv) throws 
			MalformedURLException, IOException {
		String theURL = argv.length == 0 ?
			"http://localhost/" : argv[0];
		GetURLs gu = new GetURLs(theURL);
		gu.reader.setWantedTags(GetURLs.wantTags);
		List<Element> urls = gu.reader.readTags();
		// These are XML elements that contain URLs, not URL objects
		for (Object url : urls) {
			System.out.println(url);
		}
	}
}
