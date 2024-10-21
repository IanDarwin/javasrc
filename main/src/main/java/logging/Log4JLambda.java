package logging;

// tag::main[]
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JLambda {

	private static Logger myLogger = LogManager.getLogger();

	public static void main(String[] args) {

		Person customer = getPerson();
		myLogger.info( () -> 
			"Value %d from Customer %s".formatted(customer.value, customer) );

	}
	// end::main[]

	// Minimal implementations. Excluded from book as they are not relevant to example.

	static Person getPerson() {
		Person c = new Person();
		c.name = "Robin";
		c.value = 42;
		return c;
	}

	static class Person {
		int value = 42;
		String name;
		public String toString() {
			return "Customer[" + name + "]";
		}
	}
}
