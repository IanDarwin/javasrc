package jdb;

/**
 * A Program with a Problem
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class Problem {
	public static void main(String argv[]) {
		// BEGIN
		System.out.println(System.getproperties()); // EXPECT COMPILE ERROR 
		// END
	}
}
