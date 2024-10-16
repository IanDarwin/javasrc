package numbers;

/**
 * GetNumber - program to determine if a number is float or int.
 *
 * @author Ian Darwin, https://darwinsys.com/
 */
public class GetNumber {

	// tag::main[]
	/*
	 * Process one String, displaying it as int or double as appropriate
	 */
	public static Number process(String s) {
		if (s.matches("[+-]*\\d*\\.\\d+[dDeEfF]*")) {
			try {
				double dValue = Double.parseDouble(s);
				System.out.println(s + ": is a double: " + dValue +
					" by Double.parseDouble()");
				return dValue;
			} catch (NumberFormatException e) {
				System.out.println(s + ": Invalid double: " + s);
				return Double.NaN;
			}
		} else try {
			// did not contain . d e or f, so try as long.
				long longValue = Long.parseLong(s);
				System.out.println(s + ": is a long: " + longValue +
					" by Long.parseLong()");
				return longValue;
			} catch (NumberFormatException e2) {
				System.out.println(s + ": Not a number: " + s);
				return Double.NaN;
			}
	}

	public boolean isIntegerNumber(String s) {
		Double d = Double.valueOf(s);
		boolean result = d.intValue() == d.doubleValue();
		System.out.println(s + ": is " + (result ?
			"an integer" : "a floating-point number") +
			" by Double.xxxValue() comparison");
		return result;
	}
	// end::main[]

	final static String[] demoArgs = { "156", "156.25", "23 skidoo" };

	public static void main(String[] args) {
		if (args.length == 0) {
			args = demoArgs;
		}
		for (String s : args) {
			process(s);
		}
	}
}
