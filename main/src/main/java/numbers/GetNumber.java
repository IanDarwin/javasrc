package numbers;

/**
 * GetNumber - program to determine if a number is float or int.
 *
 * @author Ian Darwin, https://darwinsys.com/
 */
public class GetNumber {

	// tag::main[]
	/*
	 * Process one String, returning it as a Number subclass
	 */
	public static void process(String s) {
		if (s.matches("[+-]*\\d*\\.\\d+[dDeEfF]*")) {
			try {
				double dValue = Double.parseDouble(s);
				System.out.println(s + ": is a double: " + dValue +
					" by Double.parseDouble()");
			} catch (NumberFormatException e) {
				System.out.println(s + ": Invalid double: " + s);
				return;
			}
		} else try {
			// did not contain . d e or f, so try as int.
				int iValue = Integer.parseInt(s);
				System.out.println(s + ": is an int: " + iValue +
					" by Integer.parseInt()");
			} catch (NumberFormatException e2) {
				System.out.println(s + ": Not a number: " + s);
				return;
			}
		Double d = Double.valueOf(s);
		System.out.println(s + ": is " + (d.intValue() == d.doubleValue() ?
			"an integer" : "a floating-point number") +
			" by Double.xxxValue() comparison");
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
