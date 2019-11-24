package jdb;

/**
 * A Program with a Problem
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Problem {
	public static void main(String argv[]) {
		// tag::main[]
		System.out.println(System.getproperties()); // EXPECT COMPILE ERROR 
		// end::main[]
	}
}
