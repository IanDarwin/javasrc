package lang;

/** A simple casting demo. */
public class CastingDemo {
	public static void main(String[] argv) {
		int i, ans;
		double d = 2.75;
		i = d;			// EXPECT COMPILE ERROR
		i = (int)d;		// with cast; i gets 2
		System.out.println("i =" + i);
		ans = (int)d * 3;		// truncate d before multiplying
		System.out.println("ans =" + ans);
		ans = (int)(d * 3);	// multiplies before truncating
		System.out.println("ans =" + ans);
	}
}
