package io;

/**
 * All the examples for the Standard output recipe.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Stdout {
	public static void main(String[] argv) {
		// BEGIN main
		Object anObject = new Object();
		String myAnswer = "no";
		int i = 42;
	
		System.out.println("Hello, World of Java");
		System.out.println("An object is " + anObject);
		System.out.println("The answer is " + myAnswer + " at this time.");
		System.out.println("The answer is " + i + '.');
		System.out.println("The answer is " + i + ".");
		System.out.println(i + '=' + " the answer.");
		System.out.println(new StringBuffer("The answer is ").append(i).append('.'));
		// END main
	}
}
