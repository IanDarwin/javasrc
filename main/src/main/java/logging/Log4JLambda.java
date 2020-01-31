package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// tag::main[]
public class Log4JLambda {

	private static Logger myLogger = LogManager.getLogger();

	public static void main(String[] args) {

		Customer customer = getCustomer();
		myLogger.info( () -> String.format(
			"Value %d from Customer %s", customer.value, customer) );

	}
// end::main[]

	// Minimal implementations. Excluded from book as they are not relevant to example.

	static Customer getCustomer() {
		Customer c = new Customer();
		c.name = "Robin";
		c.value = 42;
		return c;
	}

	static class Customer {
		int value = 42;
		String name;
		public String toString() {
			return "Customer[" + name + "]";
		}
	}
}
