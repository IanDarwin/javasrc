package starting;

import com.darwinsys.util.Debug;

public class FetchDebug {
	public static void main(String[] args) {
		String name = "poem", value;
		Fetch f = new Fetch();
		Debug.println("fetch", "Fetching " + name);
		value = f.fetch(name);
		System.out.println(value);
	}
	public String fetch(String name) {
		// in real life this would look up "name" in some table or database
		return "jabberwocky";
	}
}
