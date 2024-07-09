package numbers;

import java.util.BitSet;
import java.util.stream.IntStream;

/** Operations on series of numbers */
// tag::main[]
public class NumSeries {
	public static void main(String[] args) {

		// For ordinal list of numbers n to m, use rangeClosed(start, endInclusive)
		IntStream.rangeClosed(1, 12).forEach(
			i -> System.out.println("Month # " + i));
		
		// Or, use a for loop starting at 1.
		for (int i = 1; i <= months.length; i++)
			System.out.println("Month # " + i);
		
		// Or a foreach loop
		for (String month : months) {
			System.out.println(month);
		}
	
		// When you want a set of array indices, use range(start, endExclusive)
		IntStream.range(0, months.length).forEach(
			i -> System.out.println("Month " + months[i]));
		
		// Or, use a for loop starting at 0.
		for (int i = 0; i < months.length; i++)
			System.out.println("Month " + months[i]);

		// For e.g., counting by 3 from 11 to 27, use a for loop
		for (int i = 11; i <= 27; i += 3) {
			System.out.println("i = " + i);
		}
		
		// A discontiguous set of integers, using a BitSet

		// Create a BitSet and turn on a couple of bits.
		BitSet b = new BitSet();
		b.set(0);	// January
		b.set(3);	// April
		b.set(8);	// September

		// Print the months
		for (int i = 0; i<months.length; i++) {
			if (b.get(i))
				System.out.println("Month " + months[i]);
		}
		// Shorter way, using IntStream stream() method
		b.stream().forEach(i->System.out.println(months[i]));

		// Same example but shorter:
		// a discontiguous set of integers, using an array
		int[] numbers = {0, 3, 8};

		// Presumably somewhere else in the code... Also a foreach loop
		for (int n : numbers) {
			System.out.println("Month: " + months[n]);
		}
	}
	// tag::inner[]
	/** Names of months. See Dates/Times chapter for a better way to get these */
	protected static String months[] = {
		"January", "February", "March", "April",
		"May", "June", "July", "August",
		"September", "October", "November", "December"
	};
	// end::inner[]
}
// end::main[]
