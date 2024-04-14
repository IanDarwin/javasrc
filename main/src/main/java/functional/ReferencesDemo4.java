package functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReferencesDemo4 {

	// tag::main[]
	static final List<String> unsortedNames = List.of(
		"Gosling", "de Raadt", "Amdahl", "Turing", "Ritchie", "Hopper"
	);
	
	public static void main(String[] args) {
		List<String> names;
		
		// Sort using 
		// "an Instance Method of an Arbitrary Object of a Particular Type"
		names = new ArrayList<>(unsortedNames);
		Collections.sort(names, String::compareToIgnoreCase);   // <1>
		dump(names);

		// Equivalent Lambda:
		names = new ArrayList<>(unsortedNames);
		Collections.sort(names, 
			(str1, str2) -> str1.compareToIgnoreCase(str2));    // <2>
		dump(names);
		
		// Equivalent old way:
		names = new ArrayList<>(unsortedNames);
		Collections.sort(names, new Comparator<String>() {      // <3>
			@Override
			public int compare(String str1, String str2) {
				return str1.compareToIgnoreCase(str2);
			}
		});
		dump(names);
		
		// Simpest way, using existing comparator
		names = new ArrayList<>(unsortedNames);
		Collections.sort(names, String.CASE_INSENSITIVE_ORDER); // <4>
		dump(names);
	}
	// end::main[]

	/** Simple dumper just to show the order of the names in the array */
	private static void dump(List<String> names) {
		System.out.print(String.join(", ", names));
		System.out.println();
	}
}
