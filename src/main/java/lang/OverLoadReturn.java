/** Can you overload a method to have two different return types?
 */
public class OverLoadReturn {
	public static void main(String a[]) {
		System.out.println("Hello. Why did you call me up?");
		OverLoadReturn r = new OverLoadReturn();
		int i = method();
		String s = method();
	}
	int method() {
		System.out.println("In int method");
		return 0;
	}
	String method(String input) {	// THIS WORKS
		System.out.println("In String method(String)");
		return input;
	}

	String method() {				// EXPECT COMPILE ERROR
		System.out.println("In String method");
		return null;
	}
}
