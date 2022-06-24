package functional;

import java.time.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

/** Convert a list of Purchases to a map from each Customer 
 * who has a purchase, to a List of their purchases.
 */
public class SimpleCollectorsGroupingBy {
	static Customer chip = new Customer("Chip"),
		dale = new Customer("Dale");
	
	static List<Purchase> purchaseList = List.of(
		new Purchase(chip, LocalDate.now(), 2.34),
		new Purchase(dale, LocalDate.now().minusDays(1), 3.21),
		new Purchase(chip, LocalDate.now().minusDays(2), 6.50),
		new Purchase(dale, LocalDate.now().minusDays(3), 0.50)
	);
		
	public static void main(String[] args) {
		process(purchaseList);
	}

	static void process(List<Purchase> purchaseList) {
		Map<Customer, List<Purchase>> map = new HashMap<>();

		// Consider writing this without Java 8 mechanisms
		//	create Map of customers to purchases.
		//	for each purchase, check if customer in map;
		//	if not, map.add(customer, new List<Purchases>)
		//	then add purchase into list
		/*
		for (Purchase p : purchaseList) {
			List<Purchase> thisCustPurchases = map.get(p.customer());
			if (thisCustPurchases == null) {
				thisCustPurchases = new ArrayList<>();
				map.put(p.customer(), thisCustPurchases);
			}
			thisCustPurchases.add(p);
		}
		*/

		// A bit shorter with Streams
		map = purchaseList.stream().collect(groupingBy(Purchase::customer));

		// Now just print the map
		map.forEach((k,v) -> System.out.printf("Customer %s, purchases %s\n", k.name(), v));
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
