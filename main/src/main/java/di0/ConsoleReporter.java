package di0;

import jakarta.inject.Named;

// tag::main[]
@Named
public class ConsoleReporter implements Reporter {
	public void report(int finalResult) {
		System.out.println("ConsoleReport: Result is " + finalResult);
	}
}
// end::main[]
