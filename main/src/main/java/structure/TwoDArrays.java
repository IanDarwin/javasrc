package structure;

// This is the Two-Dimensional Array Example from around 471-5-15.
public class TwoDArrays {
	static double data[][] = {
		{ 24, 100 },
		{ 10, 24 },
		{ 32, 98.6 },
	};
	public static void main(String[] av) {
		// Each row in the initialization winds up as a column
		// (see the diagram in the course notes!!), so this
		// is a 3 x 2 array.
		System.out.println("Width   = " + data.length);
		System.out.println("Height  = " + data[0].length);
		// The last element is data[2][1], NOT [1][2]!
		System.out.println("d[2][1] = " + data[2][1]); // prints 98.6
	}
}
