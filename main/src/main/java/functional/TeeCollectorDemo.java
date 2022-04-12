package functional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Demo of "Tee Collector" (Collectors.teeing, actually)
 * Split a stream of names into two lists according to filters.
 */
public class TeeCollectorDemo {

	public static void main(String[] args) {

	var result =
		Stream.of("macOS X", "Linux", "Windows XP", "OpenBSD Unix", "Windows 10")
		.collect(Collectors.teeing(
			// first collector
			Collectors.filtering(n -> n.toLowerCase().endsWith("x"), Collectors.toList()),
			// second collector
			Collectors.filtering(n -> n.contains("w"), Collectors.toList()),
			// merge the results (must return one value)
			(List<String> list1, List<String> list2) -> List.of(list1, list2)
			));

		System.out.println(result);
	}
}
