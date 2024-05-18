import java.util.*;
import java.util.stream.*;
void main() {
	List<Integer> plusOnes =
		Stream.of(1,2,3,4,5)
		.gather(Gatherers.scan(() -> 1, (init, val) -> init + val))
		.toList()
		.forEach(System.out::println);
}
