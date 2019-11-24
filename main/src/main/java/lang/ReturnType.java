package lang;

/**
 * Simple Hello World demo program.
 */
public class ReturnType {

	int idemo() {
		return 4;
	}
	String demo() {
		return "Goodbye world";
	}

	void doTheWork() {
		String d = demo();	// assigns "Goodbye cruel world" to d
		// int e = demo();		// EXPECT COMPILE ERROR
		int e = idemo();		// Get it right
		System.out.println("d="+d);
		System.out.println("e="+e);
	}

	public static void main(String[] a) {
		new ReturnType().doTheWork();
	}
}
