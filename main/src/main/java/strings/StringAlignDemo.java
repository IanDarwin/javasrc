package strings;

public class StringAlignDemo {

	/** Demonstrate and test StringAlign class */
	public static void main(String[] argv) {
		String[] mesg = {"JavaFun", "JavaFun!" };
		for (int i=0; i<mesg.length; i++) {
			System.out.println("Input String \"" + mesg[i] + "\"");
			dump(StringAlign.Justify.LEFT, 5,
				new StringAlign(5, StringAlign.Justify.LEFT).format(mesg[i]));
			dump(StringAlign.Justify.LEFT, 10,
				new StringAlign(10, StringAlign.Justify.LEFT).format(mesg[i]));
			dump(StringAlign.Justify.CENTER, 5,
				new StringAlign(5, StringAlign.Justify.CENTER).format(mesg[i]));
			dump(StringAlign.Justify.CENTER, 10,
				new StringAlign(10, StringAlign.Justify.CENTER).format(mesg[i]));
			dump(StringAlign.Justify.RIGHT, 5,
				new StringAlign(5, StringAlign.Justify.RIGHT).format(mesg[i]));
			dump(StringAlign.Justify.RIGHT, 10,
				new StringAlign(10, StringAlign.Justify.RIGHT).format(mesg[i]));
		}
	}

	private static void dump(StringAlign.Justify format, int len, String s) {
		System.out.print(format.name().charAt(0) + "[" + len + "]");
		System.out.print(" ==> \"");
		System.out.print(s);
		System.out.print('"');
		System.out.println();
	}
}
