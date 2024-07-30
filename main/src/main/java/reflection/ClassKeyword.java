package reflection;

import java.time.LocalDate;
/**
 * Show the class keyword and getClass() method in action.
 * The class keyword can be applied to any type known at compile time.
 * 
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class ClassKeyword {
	public static void main(String[] argv) {
		// tag::main[]
		System.out.println("Trying the ClassName.class keyword:");
		System.out.println("Object class: " + Object.class);
		System.out.println("String class: " + String.class);
		System.out.println("String[] class: " + String[].class);
		System.out.println("LocalDate class: " + LocalDate.class);
		System.out.println("Current class: " + ClassKeyword.class);
		System.out.println("Class for int: " + int.class);
		System.out.println();

		System.out.println("Trying the instance.getClass() method:");
		System.out.println("Sir Robin the Brave".getClass());
		System.out.println(LocalDate.now().getClass());
		// end::main[]
	}
}
