import java.util.*;
import java.io.*;

public class ProdCons2 {

	/** Throughout the code, this is the object we synchronize on so this
	 * is also the object we wait() and notifyAll() on.
	 */
	protected LinkedList list = new LinkedList();
	protected int MAX = 10;
	protected boolean done = false; // Also protected by lock on list.

	/** Inner class representing the Producer side */
	class Producer extends Thread {

		public void run() {
			while (true) {
				Object justProduced = getRequestFromNetwork();
				// Get request from the network - outside the synch section.
				// We're simulating this actually reading from a client, and it
				// might have to wait for hours if the client is having coffee.
				synchronized(list) {
						while (list.size() == MAX) // queue "full"
						try {
							System.out.println("Producer WAITING");
							list.wait();	 // Limit the size
						} catch (InterruptedException ex) {
							System.out.println("Producer INTERRUPTED");
						}
					list.addFirst(justProduced);
					if (done)
						break;
					list.notifyAll();	// must own the lock
					System.out.println("Produced 1; List size now " + list.size());
					// yield();	// Useful for green threads & demo programs.
				}
			}
		}

		Object getRequestFromNetwork() {	// Simulation of reading from client
			// try {
			// 	Thread.sleep(10); // simulate time passing during read
			// } catch (InterruptedException ex) {
			// 	System.out.println("Producer Read INTERRUPTED");
			// }
			return(new Object());
		}
	}

	/** Inner class representing the Consumer side */
	class Consumer extends Thread {
		public void run() {
			while (true) {
				Object obj = null;
				int len = 0;
				synchronized(list) {
					while (list.size() == 0) {
						try {
							System.out.println("CONSUMER WAITING");
							list.wait();	// must own the lock
						} catch (InterruptedException ex) {
							System.out.println("CONSUMER INTERRUPTED");
						}
					}
					if (done)
						break;
					obj = list.removeLast();
					list.notifyAll();
					len = list.size();
					System.out.println("List size now " + len);
				}
				process(obj);	// Outside synch section (could take time)
				//yield(); DITTO
			}
		}

		void process(Object obj) {
			// Thread.sleep(xxx) // Simulate time passing
			System.out.println("Consuming object " + obj);
		}
	}

	ProdCons2(int nP, int nC) {
		for (int i=0; i<nP; i++)
			new Producer().start();
		for (int i=0; i<nC; i++)
			new Consumer().start();
	}

	public static void main(String[] args)
	throws IOException, InterruptedException {

		// Start producers and consumers
		int numProducers = 2;
		int numConsumers = 2;
		ProdCons2 pc = new ProdCons2(numProducers, numConsumers);

		// Let it run for, say, 30 seconds
		Thread.sleep(30*1000); 

		// End of simulation - shut down gracefully
		synchronized(pc.list) {
			pc.done = true;
			pc.list.notifyAll(); // Wake up any waiters!
		}
	}
}
