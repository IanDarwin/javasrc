package structure;

import java.util.Arrays;
import java.util.List;

public class ArraysAsListDemo {

	public static void main(String[] args) {
		List<String> firstNames = Arrays.asList("Robin", "Jaime", "Joey");
		for (String fn : firstNames) {
			System.out.println(fn);
		}
		List<String> lastNames = Arrays.asList(new String[]{"Smith", "Jones", "MacKenzie"});
		System.out.println(lastNames);
	}
}
