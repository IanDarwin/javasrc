/** Generate random ints by scaling from Math.random().
 * Prints a series of random integers from 1 to 10, inclusive.
 *
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class RandomInt {
	public static void main(String[] a) {
		for (int i=0; i<100; i++)
			System.out.println(1+(int)(Math.random() * 10));
	}
}
