package lang;

/**
 * Return multiple values wrapped in an object.
 */
public class ReturnMultiInObject {

	static class X {
		int money;
		boolean success;
	}

	public static void main(String[] args) {
		X x = proc();

		System.out.println(x.success ? "W00T" : "Meh!");
	}

	static X proc() {
		X x = new X();
		x.success = true;
		return x;
	}
}
