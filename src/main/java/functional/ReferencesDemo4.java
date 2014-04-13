package functional;

// BEGIN main
import java.util.Arrays;
import java.util.Comparator;

public class ReferencesDemo4 {

	static final String[] unsortedNames = { 
		"Gosling", "de Raadt", "Torvalds", "Ritchie", "Hopper"
	};
	
	public static void main(String[] args) {
		String[] names;
		
		// Sort using 
		// "an Instance Method of an Arbitrary Object of a Particular Type"
		names = unsortedNames.clone();
		Arrays.sort(names, String::compareToIgnoreCase);                    // <1>
		dump(names);

		// Equivalent Lambda:
		names = unsortedNames.clone();
		Arrays.sort(names, (str1, str2) -> str1.compareToIgnoreCase(str2)); // <2>
		dump(names);
		
		// Equivalent old way:
		names = unsortedNames.clone();
		Arrays.sort(names, new Comparator<String>() {                       // <3>
			@Override
			public int compare(String str1, String str2) {
				return str1.compareToIgnoreCase(str2);
			}
		});
		dump(names);
		
		// Simpest way, using existing comparator
		names = unsortedNames.clone();
		Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);                  // <4>
		dump(names);
	}
	// END main

	/** Simple dumper just to show the order of the names in the array */
	private static void dump(String[] names) {
		for (String s : names) {
			System.out.print(s);
			System.out.print(' ');
		}
		System.out.println();
	}

}
