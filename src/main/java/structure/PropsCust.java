import java.io.*;
import java.util.*;

/**
 * Use a Properties object for customizing a program.
 */
public class PropsCust {
	Properties p;
	String userName;
	String userDir;
	public static void main(String[] argv) {
		new PropsCust();
	}
	PropsCust() {
		p = new Properties();

		p.load(System.in);
		userName = p.getProperty("username", "Unknown User");
		userDir  = p.getProperty("directory", "/");

		System.out.println("Hello " + userName + ".");
		System.out.println("We are using the " + userDir + "directory today.");
	}
}
