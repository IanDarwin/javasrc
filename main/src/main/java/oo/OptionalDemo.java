package oo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

	public static void main(String[] args) {
		// tag::items[]
		List.of(
			new Item("Item 1", LocalDate.now().plusDays(7)),
			new Item("Item 2")).
				forEach(System.out::println);
		// end::items[]

		Optional<String> opt = Optional.of("What a day!");
		// opt = Optional.empty();
		if (opt.isPresent()) {
			System.out.println("Value is " + opt.get());
		} else {
			System.out.println("Value is not present.");
		}

		// Shorter way:
		System.out.println("Value is " + opt.orElse("not present"));

		// Shorter still way, when no "else" is needed
		opt.ifPresent(System.out::println);
		// or
		opt.ifPresent(s->System.out.println(s));
	}

	// tag::items[]
	static class Item {
		String name;
		Optional<LocalDate> dueDate;
		Item(String name) {
			this(name, null);
		}
		Item(String name, LocalDate dueDate) {
			this.name = name;
			this.dueDate = Optional.ofNullable(dueDate);
		}

		public String toString() {
			return "%s %s".formatted(name,
				dueDate.isPresent() ?
					"Item is due on " + dueDate.get() :
					"Sorry, do not know when item is due");
		}
	}
	// end::items[]
}
