// package lang

import java.util.List;

/**
 * Demo the "destruction" (really: destructuring) of a record
 */

record Graduate(String name, String degree, int year){}

List<?> grads = List.of(
	new Graduate("J. Smith", "M.B.A", 2019),
	new Graduate("K. Longbow", "B.Sc", 2018)
);

void main() {
	// explicit instanceof
	for (Object o : grads) {
		if (o instanceof Graduate(var nm, var deg, var year)) {
			System.out.printf("%s graduated %d with a %s\n", nm, year, deg);
		}
	}

	// pattern match in switch
	for (Object o : grads) {
		switch(o) {
			case Graduate(var nm, var deg, var year) ->
				System.out.printf("%s graduated %d with a %s\n", nm, year, deg);
			default -> {}
		}
	}
}
