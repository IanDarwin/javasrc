package starting;

// BEGIN main
/** This program exhibits some bugs, so we can use a debugger */
public class Buggy {
	static String name;

	public static void main(String[] args) {
		int n = name.length();	// bug # 1
		System.out.println(n);

		name += "; The end.";	// bug #2
		System.out.println(name); // #3
	}
}
// END main
