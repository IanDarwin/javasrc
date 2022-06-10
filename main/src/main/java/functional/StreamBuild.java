package functional;

import java.util.stream.Stream;

/**
 * Build a finite Stream, from fixed data
 */
public class StreamGenerate {

	public static void main(String[] args) {

		Stream.Builder<String> sb = Stream.builder();
		for (String str : data)
			sb.add(str);
		sb.build().forEach(System.out::println);
	}

	static String[] data = {"Fluffy", "Peter", "Roger"};
}
