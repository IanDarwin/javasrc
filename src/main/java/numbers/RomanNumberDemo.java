public class RomanNumberDemo {
	/** Simple test case */
	public static void main(String argv[]) {
		//+
		RomanNumberFormat nf = new RomanNumberFormat();
		System.out.println("Test of " + nf);
		System.out.println("0->" + nf.format(0));
		System.out.println("42->" + nf.format(42));
		System.out.println("678->" + nf.format(678));
		System.out.println("1999->" + nf.format(1999));
		System.out.println("2000->" + nf.format(2000));	// Y2K anyone?
		System.out.println("3999->" + nf.format(3999));
		System.out.println("4000->" + nf.format(4000));	// expect Exception
		//-
		// parsing not implemented.
		System.out.println("XIV->" + nf.parseObject("XIV", null));
	}
}
