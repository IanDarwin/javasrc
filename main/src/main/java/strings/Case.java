package strings;

import org.apache.commons.text.WordUtils;

/**
 * Control case.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class Case {
	public static void main(String[] argv) {
		// tag::main[]
		String name = "Java Cookbook";
		System.out.println("Normal:\t" + name);
		System.out.println("Upper:\t" + name.toUpperCase());
		System.out.println("Lower:\t" + name.toLowerCase());
		String javaName = "java cookBook"; // If it were Java identifiers :-)
		if (!name.equals(javaName))
			System.err.println("equals() correctly reports false");
		else
			System.err.println("equals() incorrectly reports true");
		if (name.equalsIgnoreCase(javaName))
			System.err.println("equalsIgnoreCase() correctly reports true");
		else
			System.err.println("equalsIgnoreCase() incorrectly reports false");
		// end::main[]

		// tag::commons-text[]
		// Fancier stuff via Apache Commons Text:
		String smith = "lorry smith";
		System.out.println(WordUtils.capitalize(smith));
		String jones = "JENkins JONes";
		System.out.println(WordUtils.capitalizeFully(jones));
		// end::commons-text[]
	}
}
