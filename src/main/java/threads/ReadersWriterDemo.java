import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Simulate multiple readers 
 * @version $Id$
 */
public class ReadersWriterDemo {
	public static void main(String[] args) {
		new ReadersWriterDemo().demo();
	}
	
	private BallotBox theData;

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	/**
	 * Constructor: set up some quasi-random initial data
	 */
	public ReadersWriterDemo() {
		List questionsList = new ArrayList();
		questionsList.add("Agree");
		questionsList.add("Disagree");
		box = new BallotBox(questionsList);
	}
	
	/**
	 * Run a demo with more readers than writers
	 */
	private void demo() {
		
		// Start two reader threads
		for (int i = 0; i < 2; i++) {
			new Thread() {
				public void run() {
					lock.readLock().lock();
					Iterator results = theData.iterator();
					lock.readLock().unlock();
					print(results);
					try {
						Thread.sleep(999);
					} catch (InterruptedException ex) {
					}
				}
			}.start();
		}
		// Start one writer thread
		new Thread() {
			public void run() {
				lock.writeLock().lock();
				theData.voteFor(0);
				try {
					Thread.sleep(1400);
				} catch (InterruptedException ex) {
				}
			}
		}.start();
		
	}
	BallotBox box;
	
	/** print the current totals */
	private void print(Iterator iter) {
		while (iter.hasNext()) {
			BallotPosition pair = (BallotPosition) iter.next();
			System.out.print(pair.getName() + "(" + pair.getVotes() + ")");
		}
		System.out.println();
	}
	

}
