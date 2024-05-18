import java.util.*;
import java.util.stream.*;
void main() {
	List<Integer> plusOnes =
		Stream.of(1,2,3,4,5)
		.gather(Gatherers.scan(() -> 1, (prev, val) -> {
				System.out.println(STR."prev \{prev}, val \{val}");
				return prev + val;
		}))
		.toList();
	plusOnes
		.forEach(System.out::println);
}
