package functional;

import java.util.stream.Stream;

/**
 * Generate a Stream, from your own data
 * Stream.generate() makes an infinite stream, needing some kind of limiter.
 */
public class StreamGenerate {
	public static void main(String[] args) {
		// Stream.generate with a Producer<T> is all you need to create your own Stream of data.
		Stream.generate(StreamGenerate::produceValue).limit(10).forEach(System.out::println);
	}

	static String[] data = {"Fluffy", "Peter", "Roger"};
	static int index;

	static String produceValue() {
		return data[index++ % data.length];
	}
}
