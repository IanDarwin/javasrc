package functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleStreamsDemo {

	static class Hero {
		String name;
		int age;

		public Hero(String name, int age) {
			this.name = name;
			this.age = age;
		}
	}

	static Hero[] heroes = { 
		new Hero("Grelber", 21), 
		new Hero("Roderick", 12),
		new Hero("Francisco", 55), 
		new Hero("Jumbletron", 22),
		new Hero("Mavericks", 0), 
		new Hero("Palladin", 50),
		new Hero("Athena", 50) };

	public static void main(String[] args) {

		long numberAdults = Arrays.stream(heroes)
				.filter(b -> b.age >= 18)
				.mapToInt(b -> b.age).count();
		System.out.println("The count of adult superheros is " + numberAdults);

		List<Object> sorted = Arrays.stream(heroes)
				.sorted((h1, h2) -> h1.name.compareTo(h2.name))
				.map(h -> h.name)
				.collect(Collectors.toList());
		System.out.println("Heroes by name: " + sorted);
	}
}
