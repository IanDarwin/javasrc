/**
 * Demonstrate use of RE's instance method sub()
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class SubDemo {
	public static void main(String[] argv) {
		//+
		// Quick demo of substitution: correct "demon" and other 
		// spelling variants to the correct, non-satanic "daemon".

		// Make an RE pattern to match almost any form (deamon, demon, etc.).
		String patt = "d[ae]{1,2}mon";

		// A test input.
		String input = "Some say Unix hath demons in it!";

		// Run it statically and see that it works.
		System.out.println(input + " --> " + RE.sub(patt, input, "deamon"));

		// Run it from a RE instance and see that it works
		RE r = new RE(patt);
		System.out.println(input + " --> " + r.sub(input, "deamon"));
		//-
	}
}
