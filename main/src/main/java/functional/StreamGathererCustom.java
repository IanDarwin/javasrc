import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Demo of a custom Stream Gatherer. Needs Java 22+
 */

// tag::main[]
// Mutable State
class DoubleHolder {
	Double value = 0.0D;
}

// Threshold for distinctness - anything over a penny is distinct
double THRESHOLD = 0.011D;

// The star of the show: a simple custom Gatherer
Gatherer<Double, DoubleHolder, Double> distinctByAmount =
	Gatherer.ofSequential(

		// Initializer
		DoubleHolder::new,

		// Integrator
		(state, element, downstream) -> {
			var distinct = Math.abs(element - state.value) > THRESHOLD;
			state.value = element;
			return distinct ? downstream.push(element) : !downstream.isRejecting();
		}
);

// Sample data
List<Double> sales = List.of(
	125.00,
	125.01,
	125.015,
	250.00,
	75.00
);

void main() {
	System.out.println("Raw");
	sales.stream().sorted().forEach(System.out::println);

	System.out.println("Distinct");
	sales.stream()
		.sorted()
		.gather(distinctByAmount)
		.forEach(System.out::println);
}
// end::main[]
