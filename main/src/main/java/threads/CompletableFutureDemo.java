package threads;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class CompletableFutureDemo {

	public static void main(String[] args) throws Exception {
		demoTrivial();
		demoSimple();
		demoThenApply();
	}
	
	private static void demoTrivial() throws Exception {
		System.out.println("CompletableFutureDemo.demoTrivial()");
		// tag::demoTrivial[]
		// Trivial case: result already known. Mainly for wrapper or combinations.
		CompletableFuture<Double> future =
				CompletableFuture.completedFuture(42D);
		System.out.println("Instant results: " + future.get());
		// end::demoTrivial[]
	}

	static void demoSimple() throws Exception {
		System.out.println("CompletableFutureDemo.demoSimple()");
		// tag::demoSimple[]
		// Simple case: nothing passed in, nothing returned from get() method
		CompletableFuture<Double> completableFuture 
			= CompletableFuture.supplyAsync(() -> 42.0);
		 
		CompletableFuture<Void> future = completableFuture
			.thenRun(() -> System.out.println("We're done here."));
		 
		System.out.println("Calling future.get(): returned " + future.get());
		// end::demoSimple[]
	}
	
	static void demoThenApply() throws Exception {
		System.out.println("CompletableFutureDemo.demoThenApply()");
		// tag::demoThenApply[]
		// Demo with thenApply() method
		Function<Double,Double> func = (dbl) -> {
			System.out.printf("CompletableFutureDemo.main(),func(%f)\n", dbl);
			return dbl * dbl;
		};

		CompletableFuture<Double> future = 
				CompletableFuture.supplyAsync(() -> Math.PI);
		final CompletableFuture<Object> result = future.thenApply(func);
		Thread.sleep(1); // Allow 1 mSec for Supplier function to run
		System.out.println("Original = " + future + ", result = " + future.get());
		System.out.println("Second   = " + result + ", result = " + result.get());
		// end::demoThenApply[]
	}

}
