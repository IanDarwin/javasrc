package lang;

/**
 * OverLoad demonstrates method overloading
 */
public class OverLoad {
	static void myfunc(int x, int y) {
		System.out.println("x = "+x+", y = "+y);
	}
	static void myfunc(int x) {
		myfunc(x, 100);		// same as "OverLoad.myfunc(x, 100);"
	}
	public static void main(String[] argv) {
		myfunc(24,24);
		myfunc(24);
	}
}
