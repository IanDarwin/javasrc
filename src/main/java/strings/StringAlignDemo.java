package strings;

public class StringAlignDemo {

	/** Demonstrate and test StringAlign class */
	public static void main(String[] argv) {
		String[] mesg = {"JavaFun", "JavaFun!" };
		for (int i=0; i<mesg.length; i++) {
			System.out.println("Input String \"" + mesg[i] + "\"");
			dump(StringAlign.JUST_LEFT, 5,
				new StringAlign(5, StringAlign.JUST_LEFT).format(mesg[i]));
			dump(StringAlign.JUST_LEFT, 10,
				new StringAlign(10, StringAlign.JUST_LEFT).format(mesg[i]));
			dump(StringAlign.JUST_CENTER, 5,
				new StringAlign(5, StringAlign.JUST_CENTER).format(mesg[i]));
			dump(StringAlign.JUST_CENTER, 10,
				new StringAlign(10, StringAlign.JUST_CENTER).format(mesg[i]));
			dump(StringAlign.JUST_RIGHT, 5,
				new StringAlign(5, StringAlign.JUST_RIGHT).format(mesg[i]));
			dump(StringAlign.JUST_RIGHT, 10,
				new StringAlign(10, StringAlign.JUST_RIGHT).format(mesg[i]));
		}
	}

	private static void dump(int format, int len, String s) {
		System.out.print((char)format + "[" + len + "]");
		System.out.print(" ==> \"");
		System.out.print(s);
		System.out.print('"');
		System.out.println();
	}
}
