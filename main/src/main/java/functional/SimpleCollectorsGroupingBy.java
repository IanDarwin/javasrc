package functional;

import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Convert a List<Transaction> to a Map from each Customer 
 * who has a Transactions, to a List of their Transactions.
 */
public class SimpleCollectorsGroupingBy {

	static Customer chip = new Customer("Chip"),
		dale = new Customer("Dale");
	
	static List<Transaction> transactionList = List.of(
		new Transaction(chip, LocalDate.now(), 2.34),
		new Transaction(dale, LocalDate.now().minusDays(1), 3.21),
		new Transaction(chip, LocalDate.now().minusDays(2), 6.50),
		new Transaction(dale, LocalDate.now().minusDays(3), 0.50)
	);
		
	public static void main(String[] args) {
		process(transactionList);
	}

	static void process(List<Transaction> transactionList) {
		Map<Customer, List<Transaction>> map = new HashMap<>();

		// Consider writing this without Java 8 mechanisms
		//	create Map of customers to Transaction.
		//	for each Transaction, check if customer in map;
		//	if not, map.add(customer, new List<Transaction>)
		//	then add Transaction into list
		/*
		for (Transaction p : transactionList) {
			List<Transaction> thisCustomerTransactions = map.get(p.customer());
			if (thisCustomerTransactions == null) {
				thisCustomerTransactions = new ArrayList<>();
				map.put(p.customer(), thisCustomerTransactions);
			}
			thisCustomerTransactions.add(p);
		}
		*/

		// A bit shorter with Streams
		map = transactionList.stream().collect(groupingBy(Transaction::customer));

		// Now just print the map
		map.forEach((k,v) -> System.out.printf("Customer %s, Transaction %s\n", k.name(), v));
	}

	record Customer(String name) {
		// Nothing to add!
	}

	record Transaction(Customer customer, LocalDate when, double amount) {
		public String toString() {
			return "Customer %s, Date %s, transaction amount %.2f".formatted(customer.name(), when, amount);
		}
	}

}
