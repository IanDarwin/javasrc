package lang;

/*
 * Test out combinations of Ands and Ors on Boolean values.
 * Some of these will probably throw a NullPointerException: which one(s)?
 * Pay attention to short-circuit evaluation; if the first subexpression
 * is known, do you need to evaluation the second??
 */
public class BooleanAndOr {
	public static void main(String[] a) {
		String s = getString();

		// These use the conventional logical "and" (&&) and "or" (||).
		try {
			if ((s != null) && (s.length() > 0))
				System.out.println("At Point One");
			if ((s != null) || (s.length() > 0))
				System.out.println("At Point Two");
		} catch (Exception e) {
			System.out.print("Logical section threw ");
			e.printStackTrace();
		}

		// These use bitwise "and" (&) and "or" (|); is it valid? What results?
		try {
			if ((s == null) & (s.length() > 0))
				System.out.println("At Point Three");
			if ((s == null) | (s.length() > 0))
				System.out.println("At Point Four");
		} catch (Exception e) {
			System.out.print("Bitwise section threw ");
			e.printStackTrace();
		}
	}

	private static String getString() {
		// Any value is fine here.
		return null;
	}
}
