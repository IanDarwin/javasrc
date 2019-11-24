package lang;

/** Silly little example that shows promotion of
 * float to double on method call.
 */
public class FtoD {
	
	public static void main(String[] args) {
		fmt(2.4f);
	}

	public static void fmt(double d) {
		System.out.println(d);
	}
}
