/**
 * StrSort demonstrates sorting of strings using a quicksort.
 * The actual sort is borrowed from the Java Demo Applet SortDemo,
 * whose copyright allows its use.
 * This may be useful on JDK1.1 (or even 1.0); on Java 2/JDK1.2,
 * use Arrays.sort() or Collections.sort() instead!
 */

public class StrSort1_1 {
	/** The list of strings to be sorted */
	static public String a[] = {
		"Qwerty",
		"Ian",
		"Java",
		"Gosling",
		"Alpha",
		"Zulu"
	};

	/** Simple main program to test the sorting */
	public static void main(String[] argv) {
		System.out.println("StrSort Demo in Java");
		StringSort s = new StringSort();
		dump(a, "Before");
		s.QuickSort(a, 0, a.length-1);
		dump(a, "After");
	}

	static void dump(String a[], String title) {
		System.out.println("***** " + title + " *****");
		for (int i=0; i<a.length; i++)
			System.out.println("a["+i+"]="+a[i]);
	}

}
