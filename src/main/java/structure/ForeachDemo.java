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
		
		List<String> list = Arrays.asList(data);
		for (String s : list)
			System.out.println(s);
// END main
	}
}
