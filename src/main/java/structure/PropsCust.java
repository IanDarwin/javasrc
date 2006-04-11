package structure;

import java.io.IOException;
import java.util.Properties;

/**
 * Use a Properties object for customizing a program.
 */
public class PropsCust {
	Properties p;
	String userName;
	String userDir;

	/** A demonstration main program */
	public static void main(String[] argv) throws IOException {
		new PropsCust().process();
	}

	/** Construct a PropsCust program */
	PropsCust() throws IOException {
		p = new Properties();

		p.load(System.in);
	}

	/** Just show some properties */
	void process() {
		userName = p.getProperty("username", "Unknown User");
		userDir  = p.getProperty("directory", "/");

		System.out.println("Hello " + userName + ".");
		System.out.println("We are using the " + userDir + "directory today.");
	}
}
