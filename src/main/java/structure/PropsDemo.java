package structure;

import java.util.Properties;

/**
 * Demonstrate the Properties class
 */
public class PropsDemo {
	public static void main(String[] argv) {
	Properties ian = new Properties();

	// Set my data.
	ian.setProperty("name", "Ian Darwin");
	ian.setProperty("favorite popsicle", "cherry");
	ian.setProperty("favorite rock group", "Fleetwood Mac");
	ian.setProperty("favorite programming language", "Java");
	ian.setProperty("pencil_color", "green");

	// should return the string "green".
	String ianColor = ian.getProperty("pencil_color");

	// Don't know what it will return.
	// BEGIN pencil.color
	String sysColor = System.getProperty("pencil_color");
	// END pencil.color

	// Now list the Properties, using System.out
	ian.list(System.out);
	}
}
