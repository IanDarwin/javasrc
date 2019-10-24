package lang;

/**
 * Built in types
 */
public class BuiltinTypes {
	public static void main(String[] argv) {
		// An integer can be entered in several ways:
		int i = 123;
		System.out.println("i = " + i);
		i = 00123;
		System.out.println("i = " + i);
		i = 0x123;
		System.out.println("i = " + i);

		// A double can also be entered in several ways:
		float f = 123f;
		System.out.println("f = " + f);
		f = 123.0f;
		System.out.println("f = " + f);
		f = (float)1.23e2;		// 1.23 x (10 ^ 2)
		System.out.println("f = " + f);
	}
}
