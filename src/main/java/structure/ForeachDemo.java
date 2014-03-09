package structure;

import java.util.Arrays;
import java.util.List;

public class ForeachDemo {
	
	public static void main(String args[]) {
		
// BEGIN main
		String[] data = { "Toronto", "Stockholm" };
		for (String s : data) {
			System.out.println(s);
		}
		
		// Show the Java 5 foreach loop - do not modernize to Java 8
		List<String> list = Arrays.asList(data);
		for (String s : list) {
			System.out.println(s);
		}
// END main
	}
}
