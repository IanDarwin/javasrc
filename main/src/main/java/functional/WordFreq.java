package functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// tag::main[]
/**
 * Implement word frequency count
 */
public class WordFreq {
	public static void main(String[] args) throws IOException {

		// Collect words with a mutable reduction into 
		// a Map<String,Long>; get entrySet as a stream,
		// sort -r, count, print highest 20.
		String fileName = args.length == 0 ?
				"src/main/java/threads/descr.txt" : args[0];
		Files.lines(Path.of(fileName))
			.flatMap(s -> Stream.of(s.split(" +")))
			.collect(Collectors.groupingBy(
				String::toLowerCase, Collectors.counting()))
			.entrySet().stream()
			.sorted(Map.Entry.<String,Long>comparingByValue() 
			.reversed())
			.limit(20)
			.map(entry -> String.format("%4d %s", entry.getValue(), entry.getKey()))
			.forEach(System.out::println);
	}
}
// end::main[]
