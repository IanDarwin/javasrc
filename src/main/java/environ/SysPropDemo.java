import java.util.*;
/**
 * List one or more item(s) from System Properties
 */
public class SysPropDemo {
	public static void main(String argv[]) {
		System.out.println("System Properties:");
		if (argv.length == 0)
			System.getProperties().list(System.out);
		else for (int i=0; i<argv.length; i++) {
			String s = argv[i];
		 	System.out.println(s + " = " + 
				System.getProperties().getProperty(s));
		}
	}
}
