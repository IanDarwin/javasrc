import java.util.*;
import java.util.stream.*;
void main() {
	List<Integer> plusOnes =
		Stream.of(1,2,3,4,5)
		.gather(Gatherers.scan(() -> 1, (prev, val) -> {
				System.out.printf("prev %d, val %", prev, val);
				return prev + val;
		}))
		.toList();
	plusOnes
		.forEach(System.out::println);
}
