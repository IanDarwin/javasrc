import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Demo of a custom Stream Gatherer.
 */

// end::main[]
// Mutable State
class DoubleHolder {
	Double value = 0.0D;
}

// The star of the show: a simple custom Gatherer
Gatherer<Double, DoubleHolder, Double> distinctByAmount =
	Gatherer.ofSequential(

		// Initializer
		DoubleHolder::new,

		// Integrator
		(state, element, downstream) -> {
			var distinct = Math.abs(element - state.value) > 0.011D;
			state.value = element;
			return distinct ? downstream.push(element) : !downstream.isRejecting();
		},

		// FINISHER
		Gatherer.defaultFinisher()
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
