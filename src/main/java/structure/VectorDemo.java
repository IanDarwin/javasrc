import java.util.*;

/**
 * Simple demo of the Vector class program.
 */
public class VectorDemo {
	public static void main(String argv[]) {
		Vector v;
		Enumeration e;

		v = new Vector();

		// Add lots of elements to the Vector...
		v.addElement(new Date(96,04,01));
		v.addElement(new Date(95, 8,15));
		v.addElement(new Date(94,04,04));

		// First print them out using a for loop.
		System.out.println("Retrieving by index:");
		for (int i = 0; i<v.size(); i++)
			System.out.println("Element " + i + " = " + v.elementAt(i));
		// Now print them all out using Enumeration. How many? Don't 
		// know or care, Vector knows for us.
		System.out.println("Retrieving by Enumeration:");
		e = v.elements();
		while (e.hasMoreElements()) {
			Object i = e.nextElement();
			System.out.println(i/*.toString()*/);
		}
	}
}
