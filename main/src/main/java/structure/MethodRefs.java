package structure;

import java.util.*;

public class MethodRefs {

	public static void main(String[] args) {
		List<String> list = 
			List.of("Welcome", "Bienvendios", "Guten Tag", "Bienvenue");

		list.forEach(System.out::println);
	}
}
