import java.util.prefs.Preferences;

public class PrefsDemo {
	public static void main(String[] args) {
		Preferences prefs = Preferences.userNodeForPackage(PrefsDemo.class);
		String text    = prefs.get("textFontName", "lucida-bright");
		String display = prefs.get("displayFontName", "lucida-blackletter");
		System.out.println(text);
		System.out.println(display);
		prefs.put("textFontName", "times-roman");
		prefs.put("displayFontName", "helvetica");
	}
}
