package structure;

import java.util.List;

/**
 * The modern way to initialize a List with known values
 */
public class InitializeList {

	public static void main(String[] args) {
		var myList = List.of("Harry", "Hermione", "Ron");
		
		System.out.println(myList);
	}

}
