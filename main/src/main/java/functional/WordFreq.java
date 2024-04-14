package functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// tag::main[]
/**
 * Implement word frequency count; shown in to main steps
 * (WordFreq2 shows the steps combined into one statement).
 */
public class WordFreq {
	public static void main(String[] args) throws IOException {

		String fileName = args.length == 0 ?  "README.adoc" : args[0];

		// Collect words with a mutable reduction into
		// a Map<String,Long>
		Map<String,Long> map = Files.lines(Path.of(fileName))
			.filter(line->!line.isEmpty())
			.flatMap(s -> Stream.of(s.split(" ")))
			.collect(Collectors.groupingBy(
				String::toLowerCase, Collectors.counting()));

		// Get map's entrySet as a stream,
		// sort -r, count, print highest 20.
		map.entrySet().stream()
			.sorted(Map.Entry.<String,Long>comparingByValue() 
			.reversed())
			.limit(20)
			.map(entry -> "%4d %s".formatted(entry.getValue(), entry.getKey()))
			.forEach(System.out::println);
	}
}
// end::main[]
