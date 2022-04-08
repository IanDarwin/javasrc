package threads;

import java.util.concurrent.*;

// tag::main[]
public class RunnableLambda {

	private static ExecutorService threadPool = Executors.newSingleThreadExecutor();

	public static void main(String[] args) {
		threadPool.submit(() -> System.out.println("Hello from a thread"));
		// end::main[]
		threadPool.shutdown();
	}
}
