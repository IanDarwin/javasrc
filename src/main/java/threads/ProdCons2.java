import java.util.*;
import java.io.*;

public class ProdCons2 {

	protected LinkedList list = new LinkedList();

	protected boolean done = false;

	class Producer extends Thread {
		public void run() {
			while (!done) {
				int len = 0;
				synchronized(this) {
					Object justProduced = new Object();
					list.addFirst(justProduced);
					len = list.size();
					notifyAll();
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						System.out.println("Producer interrupted, bye");
						return;
					}
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
				synchronized(this) {
					while (list.size() == 0) {
						try {
							System.out.println("CONSUMER WAITING");
							wait();
						} catch (InterruptedException ex) {
							return;
						}
					}
					System.out.println("Consuming object " + obj);
					obj = list.removeLast();
					notifyAll();
					len = list.size();
				}
				System.out.println("List size now " + len);
			}
		}
	}

	ProdCons2() {
		new Producer().start();
		new Consumer().start();
		// new Consumer().start();
	}

	public static void main(String[] args) throws IOException {
		ProdCons2 pc = new ProdCons2();
	}
}
