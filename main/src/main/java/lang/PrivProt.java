package lang;

/** Member modifier "private protected" is now gone from the language!
 * This class proves it: modern Java compilers must reject this file.
 */
class PrivProt0 {
	private protected int a;	// EXPECT COMPILE ERROR
	public int b;

	PrivProt0(int ia, int ib) {
		a = ia; b = ib;
	}
}

public class PrivProt {
	public static void main(String[] s) {
		PrivProt0 a = new PrivProt0(2,4);
		int x = a.a;			// EXPECT COMPILE ERROR
		int y = a.b;
		System.out.println("Here they are: "+x+','+y);
	}
}
