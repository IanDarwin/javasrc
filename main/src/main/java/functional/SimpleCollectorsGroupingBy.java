package functional;

import java.time.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

public class SimpleCollectorsGroupingBy {
	static Customer chip = new Customer("Chip"),
		dale = new Customer("Dale");
	
	static List<Purchase> purchases = List.of(
		new Purchase(chip, LocalDate.now(), 2.34),
		new Purchase(dale, LocalDate.now().minusDays(1), 3.21),
		new Purchase(chip, LocalDate.now().minusDays(2), 6.50),
		new Purchase(dale, LocalDate.now().minusDays(3), 0.50)
	);
		
	public static void main(String[] args) {
		Map<Customer, List<Purchase>> result = 
			// Consider writing this without Streams:
			//	create Map of customers to purchases.
			//  for each purchase, check if customer in map, and
			//		add(customer, new List<Purchases>) if not 
			//    then get list from map, and add purchase into it.
			purchases
			.stream()
			.collect(
				groupingBy(Purchase::customer));

		// Now just print the map
		result.forEach((k,v) -> System.out.printf("Customer %s, purchases %s\n", k.name(), v));
	}

	record Customer(String name) {
		// Nothing to add!
	}

	record Purchase(Customer customer, LocalDate when, double amount) {
		public String toString() {
			return String.format("Purchaser %s, Date %s, purchase %.2f", customer.name(), when, amount);
		}
	}

}
