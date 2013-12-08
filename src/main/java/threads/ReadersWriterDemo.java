package threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Simulate multiple readers reading from a ReadWriteLock
 * that one writer is writing to.
 */
public class ReadersWriterDemo {
	private static final int NUM_READER_THREADS = 3;

	public static void main(String[] args) {
		new ReadersWriterDemo().demo();
	}

	/** Set this to true to end the program */
	private boolean done = false;

	/** The data being protected. */
	private BallotBox theData;

	/** The read lock / write lock combination */
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * Constructor: set up some quasi-random initial data
	 */
	public ReadersWriterDemo() {
		List<String> questionsList = new ArrayList<String>();
		questionsList.add("Agree");
		questionsList.add("Disagree");
		questionsList.add("No opinion");
		theData = new BallotBox(questionsList);
	}

	/**
	 * Run a demo with more readers than writers
	 */
	private void demo() {

		// Start two reader threads
		for (int i = 0; i < NUM_READER_THREADS; i++) {
			new Thread() {
				public void run() {
					while (!done) {
						Iterator<BallotPosition> results = null;
						lock.readLock().lock();
						try {
							results = theData.iterator();
						} finally {
							// Unlock in finally to be sure it gets done.
							lock.readLock().unlock();
						}
						// Now that lock has been freed, take time to print
						// (note this is not totally threadsafe as the
						// individual BallotPositions can still be updated
						// while this thread is reading from them; since
						// the ballot counts are ints this will work, but
						// with more complex objects you might need to 
						// make a deep copy of the objects where I
						// just call iterator() above...).
						print(results);
						try {
							Thread.sleep(((long)(Math.random()* 1000)));
						} catch (InterruptedException ex) {
							// nothing to do
						}
					}
				}
			}.start();
		}
		// Start one writer thread to simulate occasional voting
		new Thread() {
			public void run() {
				while (!done) {
					lock.writeLock().lock();
					try {
						theData.voteFor(
							// Vote for random candidate :-)
							// Performance: should have one PRNG per thread.
							(((int)(Math.random()*
							theData.getCandidateCount()))));
					} finally {
						lock.writeLock().unlock();
					}
					try {
						Thread.sleep(((long)(Math.random()*1000)));
					} catch (InterruptedException ex) {
						// nothing to do
					}
				}
			}
		}.start();

		// In the main thread, wait a while then terminate the run.
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException ex) {
			// nothing to do
		} finally {
			done = true;
		}
	}

	/** print the current totals */
	private void print(Iterator<BallotPosition> iter) {
		boolean first = true;
		while (iter.hasNext()) {
			BallotPosition pair = iter.next();
			if (!first)
				System.out.print(", ");
			System.out.print(pair.getName() + "(" + pair.getVotes() + ")");
			first = false;
		}
		System.out.println();
	}
}
