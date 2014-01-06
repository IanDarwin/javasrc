package functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleStreamDemo {

	static class Hero {
		String name;
		int age;

		public Hero(String name, int age) {
			this.name = name;
			this.age = age;
		}
	}
	// BEGIN main
	static Hero[] heroes = { 
		new Hero("Grelber", 21), 
		new Hero("Roderick", 12),
		new Hero("Francisco", 35), 
		new Hero("Superman", 65),
		new Hero("Jumbletron", 22),
		new Hero("Mavericks", 1), 
		new Hero("Palladin", 50),
		new Hero("Athena", 50) };

	public static void main(String[] args) {

		long adultYearsExperience = Arrays.stream(heroes)
				.filter(b -> b.age >= 18)
				.mapToInt(b -> b.age).sum();
		System.out.println("We're in good hands! The adult superheros have " + 
				adultYearsExperience + " years of experience");

		List<Object> sorted = Arrays.stream(heroes)
				.sorted((h1, h2) -> h1.name.compareTo(h2.name))
				.map(h -> h.name)
				.collect(Collectors.toList());
		System.out.println("Heroes by name: " + sorted);
	}
	// END main
}
