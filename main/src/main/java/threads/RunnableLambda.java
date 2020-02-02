package threads;

import java.util.concurrent.*;

// tag::main[]
public class RunnableLambda {

	public static void main(String[] args) {
		threadPool.submit(() -> System.out.println("Hello from a thread"));
		// end::main[]
		threadPool.shutdown();
	}
	private static ExecutorService threadPool = Executors.newSingleThreadExecutor();
}
