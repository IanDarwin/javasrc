import java.util.*;
import java.io.*;

public class ProdCons2 {

	/** Throughout the code, this is the object we synchronize on so this
	 * is also the object we wait() and notifyAll() on.
	 */
	protected LinkedList list = new LinkedList();

	protected boolean done = false;

	class Producer extends Thread {
		public void run() {
			while (!done) {
				int len = 0;
				synchronized(list) {
					Object justProduced = new Object();
					list.addFirst(justProduced);
					len = list.size();
					list.notifyAll();	// must own the lock
				}
				System.out.println("Produced 1; List size now " + len);
				yield();
			}
		}
	}

	class Consumer extends Thread {
		public void run() {
			while (!done) {
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
					obj = list.removeLast();
					System.out.println("Consuming object " + obj);
					len = list.size();
				}
				System.out.println("List size now " + len);
				yield();
			}
		}
	}

	ProdCons2() {
		new Producer().start();
		new Producer().start();
		new Consumer().start();
		new Consumer().start();
	}

	public static void main(String[] args) throws IOException {
		ProdCons2 pc = new ProdCons2();
	}
}
