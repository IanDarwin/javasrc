package environ;

import java.io.IOException;

/**
 * List one or more item(s) from System Properties
 */
public class SysPropDemo {
	public static void main(String[] argv) throws IOException {
		if (argv.length == 0)
			// BEGIN sysprops
			System.getProperties().list(System.out);
			// END sysprops
		else for (int i=0; i<argv.length; i++) {
			String s = argv[i];
		 	System.out.println(s + " = " + 
				System.getProperties().getProperty(s));
		}
	}
}
