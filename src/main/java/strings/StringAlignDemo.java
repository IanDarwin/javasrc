public class StringFormatDemo {

	/** Demonstrate and test StringFormat class */
	public static void main(String[] argv) {
		String[] mesg = {"JavaFun", "JavaFun!" };
		for (int i=0; i<mesg.length; i++) {
			System.out.println("Input String " + mesg[i]);
			dump(StringFormat.JUST_LEFT,
				new StringFormat(5, StringFormat.JUST_LEFT).format(mesg[i]));
			dump(StringFormat.JUST_LEFT,
				new StringFormat(10, StringFormat.JUST_LEFT).format(mesg[i]));
			dump(StringFormat.JUST_CENTER,
				new StringFormat(5, StringFormat.JUST_CENTER).format(mesg[i]));
			dump(StringFormat.JUST_CENTER,
				new StringFormat(10, StringFormat.JUST_CENTER).format(mesg[i]));
			dump(StringFormat.JUST_RIGHT,
				new StringFormat(5, StringFormat.JUST_RIGHT).format(mesg[i]));
			dump(StringFormat.JUST_RIGHT,
				new StringFormat(10, StringFormat.JUST_RIGHT).format(mesg[i]));
		}
	}

	private static void dump(int format, String s) {
		System.out.print((char)format);
		System.out.print(" ==> \"");
		System.out.print(s);
		System.out.print('"');
		System.out.println();
	}
}
