import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Simulate multiple readers 
 * @version $Id$
 */
public class ReadersWriterDemo {
	private static final int NUM_READER_THREADS = 3;
	public static void main(String[] args) {
		new ReadersWriterDemo().demo();
	}
	
	private boolean done = false;
	
	private BallotBox theData;

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	/**
	 * Constructor: set up some quasi-random initial data
	 */
	public ReadersWriterDemo() {
		List questionsList = new ArrayList();
		questionsList.add("Agree");
		questionsList.add("Disagree");
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
					while(!done) {
						Iterator results = null;
						try {
							lock.readLock().lock();
							results = theData.iterator();
						} finally {
							// Unlock in finally to be sure.
							lock.readLock().unlock();
						}
						// Now lock has been freed, take time to print
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
				while(!done) {
					try {
						lock.writeLock().lock();
						theData.voteFor(
								(((int)(Math.random()*
								theData.getCandidateCount()))));
					} finally {
						lock.writeLock().unlock();
					}
					try {
						Thread.sleep(((long)(Math.random()*1500)));
					} catch (InterruptedException ex) {
						// nothing to do
					}
				}
			}
		}.start();
		
		// In the main thread, wait a while then terminate the run.
		try {
			Thread.sleep(10 *1000);
		} catch (InterruptedException ex) {
			// nothing to do
		} finally {
			done = true;
		}	
	}

	/** print the current totals */
	private void print(Iterator iter) {
		boolean first = true;
		while (iter.hasNext()) {
			BallotPosition pair = (BallotPosition) iter.next();
			if (!first)
				System.out.print(", ");
			System.out.print(pair.getName() + "(" + pair.getVotes() + ")");
			first = false;
		}
		System.out.println();
	}
	

}
