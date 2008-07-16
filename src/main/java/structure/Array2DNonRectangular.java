package structure;

/**
 * Creates and prints a non-rectangular 2D array
 */
public class Array2DNonRectangular {
	public static void main(String[] args) {
		
		// Declare 2D array, and allocate 1st dimension
		int[][] data = new int[10][];

		// Allocate 2nd dimension, non-rectangular
		for (int i = 0; i < data.length; i++) {
			data[i] = new int[i];
		}
		
		// Print the array
		for (int[] d : data) {
			System.out.print("Row length " + d.length + ": ");
			for (int i : d) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
