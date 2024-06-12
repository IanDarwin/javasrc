package structure;

/** Show Two-Dimensional Array of Objects */
// tag::main[]
public class ArrayTwoDObjects {

	/** Return list of subscript names (unrealistic; just for demo). */
	public static String[][] computedArray() {
		String info[][];
		info = new String[10][10];
		for (int i=0; i < info.length; i++) {
			for (int j = 0; j < info[i].length; j++) {
				info[i][j] = "String[" + i + "," + j + "]";
			}
		}
		return info;
	}

	static String[][] inittedArray = {
		{ "One", "Two" },
		{ "Buckle my shoe" },
	};

	/** Run the initialization method and print part of the results */
	public static void main(String[] args) {
		print("from computedArray", computedArray());
		print("from initted array", inittedArray);
	}

	/** Print selected elements from the 2D array */
	public static void print(String tag, String[][] array) {
		System.out.println("Array " + tag + " is of length " + array.length);
		for (int i=0; i < 2; i++) {
			System.out.println("Row " + i + " is " + array[i].length);
			for (int j = 0; j < Math.min(array[i].length, 3); j++) {
				System.out.println(STR."Array[\{i}][\{j}] = \{array[i][j]}");
			}
		}
	}
}
// end::main[]
