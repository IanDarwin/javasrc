/**
 * Test the IMath class
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class IMathTest {
	public static void main(String[] argv) {
		//+
		for (int i=-10; i<=25; i+=2)
			System.out.println("isqrt(" + i + ") = " + IMath.isqrt(i));
		//-
	}
}
