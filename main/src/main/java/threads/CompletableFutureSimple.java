import java.util.concurrent.*;

// tag::main[]
class CompletableFutureSimple {
	static String twice(String x) { return x + ' ' + x; }

	public static void main(String[] args) {	
		CompletableFuture<String> cf = new CompletableFuture<>();
		cf.thenApply(x -> twice(x))
		  .thenAccept(x -> System.out.println(x));
		// Possibly some computation going on here... Then:
		cf.complete("Hello");
	}
}
// end::main[]
