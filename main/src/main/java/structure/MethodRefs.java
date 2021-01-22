package structure;

import java.util.*;

// Show List.of() method as well as new List.forEach with a method ref
public class MethodRefs {

	public static void main(String[] args) {
		List<String> list = 
			List.of("Welcome", "Bienvendios", "Guten Tag", "Bienvenue");

		list.forEach(System.out::println);
	}
}
