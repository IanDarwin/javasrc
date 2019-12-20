import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * Implement Unix "wc(1)" aka word count, in two (long) statements
 * 1) Count words using Streams with a mutable reduction into Map<String,int>.
 * 2) Print results sorted numerically descending, limit 20
 */
public class WordCount {
	static String curWord="";
	static int n=0;
		public static void main(String[] args) throws IOException {

		Map<String,Integer> map = Files.lines(Path.of(args[0]))
			.map(String::toLowerCase)
			.flatMap(s->Stream.of(s.split(" +")))
			.sorted()
			.collect(HashMap::new, (m,s)->{
				if (s.equals(curWord))
					++n;
				else
					m.put(s, m.getOrDefault(s,0)+1);n=0;
				}, HashMap::putAll);

		map
			.keySet()
			.stream()
			.sorted((s1,s2) -> (map.get(s2).compareTo(map.get(s1))))
			.map(s->String.format("%4d %s", map.get(s),s))
			.limit(20)
			.forEach(System.out::println);
	}
}
