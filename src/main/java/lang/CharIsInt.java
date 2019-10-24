package lang;

public class CharIsInt {
	public static void main(String[] argv) {
		char a = 'A';
		System.out.println("a is " + a);
		int i = a;
		System.out.println("i is " + i);
		char n = (char)-a;
		System.out.println("n is " + n);
		int z = n;
		System.out.println("z is " + z);
		// Now: is char signed or unsigned?
		// char neg = -1;		// WON'T COMPILE
		// System.out.println("neg is " + neg);
	}
}
