package structure;

import java.util.prefs.Preferences;

/**
 * Simple demonstration of using Preferences.
 * @version $Id$
 */
public class PrefsDemo {
	public static void main(String[] args) {

		// Setup the Preferences for this application, by class.
		Preferences prefs = Preferences.userNodeForPackage(PrefsDemo.class);

		// Retrieve some preferences previously stored, with defaults in case
		// this is the first run.
		String text    = prefs.get("textFontName", "lucida-bright");
		String display = prefs.get("displayFontName", "lucida-blackletter");
		System.out.println(text);
		System.out.println(display);

		// Assume the user chose new preference values: Store them back.
		prefs.put("textFontName", "times-roman");
		prefs.put("displayFontName", "helvetica");
	}
}
