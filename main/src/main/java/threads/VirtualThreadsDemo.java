package threads;

import java.util.concurrent.*;
import java.util.stream.*;

public class VirtualThreadsDemo {

	final static int MAX = 5;

	// tag::main[]
	public static void main(String[] args) throws Exception {

		Runnable r = () -> {
			System.out.println("Hello from " + Thread.currentThread()); 
		};

		// Start vthreads with an ExecutorService
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			IntStream.range(0, MAX).forEach(i -> executor.submit(r));
			executor.submit(r);
		}

		// Start vthreads directly
		IntStream.range(0,MAX).forEach(i -> Thread.startVirtualThread(r));

		Thread.sleep(100);
	}
	// end::main[]
}
