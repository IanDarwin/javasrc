/**
 * Can you change the .length of an array?
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class ChangeArrayLength {
	public static void main(String[] argv) {
		//+
		int[] a = new int[4];
		System.out.println(a.length);
		a.length = 5;
		//-
	}
}
