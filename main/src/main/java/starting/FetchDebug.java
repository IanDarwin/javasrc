package starting;

import java.util.logging.Logger;

public class FetchDebug {
	private static Logger logger = Logger.getLogger(FetchDebug.class.getSimpleName());
	public static void main(String[] args) {
		String name = "poem", value;
		Fetch f = new Fetch();
		logger.info("Fetching " + name);
		value = f.fetch(name);
		System.out.println(value);
	}
	public String fetch(String name) {
		// in real life this would look up "name" in some table or database
		return "jabberwocky";
	}
}
