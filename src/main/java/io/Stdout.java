/**
 * All the examples for the Standard output recipe.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Stdout {
	public static void main(String argv[]) {
		//+
		String myAnswer = "no";
		int i = 42;
	
		System.out.println("Hello, World of Java");
		System.out.println("The answer is " + myAnswer + " at this time.");
		System.out.println("The answer is " + i + '.');
		System.out.println("The answer is " + i + ".");
		System.out.println(new StringBuffer("The answer is ").append(i).append('.'));
		//-
	}
}
