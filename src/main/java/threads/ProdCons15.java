package threads;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** Producer-Consumer in Java, for J2SE 1.5+ using concurrent.
 */
// BEGIN main
public class ProdCons15 {

	protected volatile boolean done = false;

	/** Inner class representing the Producer side */
	class Producer implements Runnable {

		protected BlockingQueue<Object> queue;

		Producer(BlockingQueue<Object> theQueue) { this.queue = theQueue; }

		public void run() {
			try {
				while (true) {
					Object justProduced = getRequestFromNetwork();
					queue.put(justProduced);
					System.out.println(
						"Produced 1 object; List size now " + queue.size());
					if (done) {
						return;
					}
				}
			} catch (InterruptedException ex) {
				System.out.println("Producer INTERRUPTED");
			}
		}

		Object getRequestFromNetwork() {	// Simulation of reading from client
			try {
					Thread.sleep(10); // simulate time passing during read
			} catch (InterruptedException ex) {
			 	System.out.println("Producer Read INTERRUPTED");
			}
			return new Object();
		}
	}

	/** Inner class representing the Consumer side */
	class Consumer implements Runnable {
		protected BlockingQueue<Object> queue;

		Consumer(BlockingQueue<Object> theQueue) { this.queue = theQueue; }

		public void run() {
			try {
				while (true) {
					Object obj = queue.take();
					int len = queue.size();
					System.out.println("List size now " + len);
					process(obj);
					if (done) {
						return;
					}
				}
			} catch (InterruptedException ex) {
					System.out.println("CONSUMER INTERRUPTED");
			}
		}

		void process(Object obj) {
			// Thread.sleep(123) // Simulate time passing
			System.out.println("Consuming object " + obj);
		}
	}

	ProdCons15(int nP, int nC) {
		BlockingQueue<Object> myQueue = new LinkedBlockingQueue<>();
		for (int i=0; i<nP; i++)
			new Thread(new Producer(myQueue)).start();
		for (int i=0; i<nC; i++)
			new Thread(new Consumer(myQueue)).start();
	}

	public static void main(String[] args)
	throws IOException, InterruptedException {

		// Start producers and consumers
		int numProducers = 4;
		int numConsumers = 3;
		ProdCons15 pc = new ProdCons15(numProducers, numConsumers);

		// Let the simulation run for, say, 10 seconds
		Thread.sleep(10*1000);

		// End of simulation - shut down gracefully
		pc.done = true;
	}
}
// END main
