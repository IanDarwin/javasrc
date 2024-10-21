package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// tag::main[]
public class RunnableLambda {

	private static ExecutorService threadPool =
		Executors.newSingleThreadExecutor();

	public static void main(String[] args) {
		threadPool.submit(() -> System.out.println("Hello from a thread"));
		// end::main[]
		threadPool.shutdown();
	}
}
