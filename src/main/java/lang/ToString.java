package lang;

/**
 * ToString -- demo program to show a toString method.
 */
public class ToString {
	int value;

	ToString() {
		value = 0;
	}
	ToString(int it) {
		value = it;
	}
	public String toString() {
		return "-->" + value + "<--";
	}

	public static void main(String[] argv) {
		ToString s = new ToString(123);

		System.out.println("Hello, World of Java");
		System.out.println("My object is" + s);
	}
}
