package structure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A Properties file specialized to read a UNIX user's MH Mail System profile.
 */
public class MHProperties extends java.util.Properties {
	public static String PROFILE_NAME = ".mh_profile";

	public MHProperties() {
		super();
		try {
			load();
		} catch (FileNotFoundException ex) {
			System.err.println("You do not appear to have a " + PROFILE_NAME);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	/** Load the .mhprofile; like load(InputStream) but simpler. */
	public void load() throws IOException {

		String fName = System.getProperty("user.home") +
			System.getProperty("file.separator") +
			PROFILE_NAME;
		BufferedReader is = new BufferedReader(new FileReader(fName));
		String line;
		while ((line = is.readLine()) != null) {
			if (line.startsWith("#"))
				continue;
			int where = line.indexOf(':');
			put(line.substring(0, where), line.substring(where+1).trim());
		}
	}
}
