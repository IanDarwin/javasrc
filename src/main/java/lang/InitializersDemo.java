package lang;

/* To show that certain things really must be initialized */

class InitializersDemo {
	public Object o;
	public int i;

	public static void main(String[] av) {
		Object mo;
		int mi;
		InitializersDemo t = new InitializersDemo();
		if (t.o == null)
			System.out.println("o is null");
		if (t.i == 0)
			System.out.println("i is zero");
		if (mo == null)		// EXPECT COMPILE ERROR
			System.out.println("mo is null");
		if (mi == 0)		// EXPECT COMPILE ERROR
			System.out.println("mi is zero");
	}
}
