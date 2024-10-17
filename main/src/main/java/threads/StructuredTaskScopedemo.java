import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;

// tag::main[]
private static final ScopedValue<Client> CLIENT = ScopedValue.newInstance();

private final Client currentClient = new Client(1234);

void main() {

	ScopedValue.runWhere(CLIENT, currentClient, () -> {
		try (var scope = new StructuredTaskScope<Object>()) {
			scope.fork(doSomething);
			scope.fork(doSomethingElse);
			scope.fork(doYetMore);
			scope.join();
		} catch (InterruptedException iex) {
			System.out.println("Uncool! You interrupted me.");
		}
	});
}

Callable<String> doSomething = () -> {
	System.out.println("In doSomething, client " + CLIENT.get());
	return "One";
};
Callable<String> doSomethingElse = () -> {
	System.out.println("In doSomethingElse, not much here.");
	return "Two";
};
Callable<Integer> doYetMore = () -> {
	System.out.println("In doYetMore, using client " + CLIENT.get());
	return 42;
};

record Client(int id) { }
// end::main[]
