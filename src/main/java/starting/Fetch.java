package starting;

public class Fetch {
	public static void main(String[] args) { 
		String name = "poem", value;
		Fetch f = new Fetch();
		if (System.getProperty("debug.fetch") != null) {
			System.err.println("Fetching " + name);
		}
		value = f.fetch(name);
	}
	public String fetch(String name) {
		// in real life this would look up "name" in some table or database
		return "jabberwocky";
	}
}
