package numbers;

import java.util.BitSet;

/** Operations on series of numbers */
public class NumSeries {
	public static void main(String[] args) {

		// When you want an ordinal list of numbers, use a for loop
		// starting at 1.
		for (int i = 1; i <= months.length; i++)
			System.out.println("Month # " + i);
	
		// When you want a set of array indices, use a for loop
		// starting at 0.
		for (int i = 0; i < months.length; i++)
			System.out.println("Month " + months[i]);

		// For a discontiguous set of integers, try a BitSet

		// Create a BitSet and turn on a couple of bits.
		BitSet b = new BitSet();
		b.set(0);	// January
		b.set(3);	// April

		// Presumably this would be somewhere else in the code.
		for (int i = 0; i<months.length; i++) {
			if (b.get(i))
				System.out.println("Month " + months[i] + " requested");
		}
	}
	/** The names of the months. See Dates/Times chapter for a better way */
	protected static String months[] = {
		"January", "February", "March", "April",
		"May", "June", "July", "August",
		"September", "October", "November", "December"
	};
}
