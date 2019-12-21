import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * Implement word frequency count, in two (long) statements
 */
public class WordFreq {
	public static void main(String[] args) throws IOException {

 		// 1) Collect words with a mutable reduction into Map<String,Integer>.
		Map<String,Integer> map = Files.lines(Path.of(args[0]))
			.map(String::toLowerCase)
			.flatMap(s->Stream.of(s.split(" +")))
			.collect(HashMap::new, 
				(m,s)->m.put(s, m.getOrDefault(s,0)+1),
				HashMap::putAll);

		// 2) Print results sorted numerically descending, limit 20
		map .keySet()
			.stream()
			.sorted((s1,s2) -> (map.get(s2).compareTo(map.get(s1))))
			.map(s->String.format("%4d %s", map.get(s),s))
			.limit(20)
			.forEach(System.out::println);
	}
}
