package oo;

import java.util.List;

/**
 * An example of Data Oriented Programming (DOP); see Brian Goetz,
 * https://www.infoq.com/articles/data-oriented-programming-java/
 */
public class DataOrientedDemo {

	// tag::main[]
	sealed interface Transaction permits Order, Refund {}	// <1>
	
	record Order(String product, int quantity, 				// <2>
		double price) implements Transaction {}

	record Refund(String reason, 
		double amount) implements Transaction {}

	record Customer(String name, String address, 			// <3>
		String State, List<Transaction> orders) {}

	void process(List<Customer> customers) {
		for (Customer c : customers) {
			double balance = 0;								// <4>
			for (Transaction tx: c.orders) {
				switch (tx) {
				case Order(String p, int q, double price) -> {	// <5>
					System.out.printf("Invoice %s for %d %d for $%7.2f\n",
						c.name(), q, p, price);
					balance += price;
				}
				case Refund(String reason, double amount) -> {
					System.out.printf("Credit %s $%7.2f due to %s\n",
						c.name(), amount, reason);
					balance -= amount;
				}
				//											<6>
				}
			}
			System.out.printf("Net balance for %s is %f", c.name(), balance); // <7>
		}
	} 

	void main() {
		List<Transaction> txlist = List.of(new Order("Widgets", 10, 200.00),
			new Order("Whatzits", 5, 125.00),				// <8>
			new Refund("Lost shipment #456", 175.00));
		var cust = new Customer("Whizzy Systems Inc", "123 Erewhon St", "Confusion", txlist);
		process(List.of(cust));
	}
	// end::main[]
}
