package threads;

import java.lang.Thread.UncaughtExceptionHandler;

/** 
 * Testbed for answering the question:
 * Who gets the InterruptedException, anyway?
 * under differing circumstances (comment out parts to vary it).
 */
public class IntrWhoCatches {

	static Runnable r = new Runnable() {
		public void run() {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Interrupt caught in Runnable, on " + Thread.currentThread());
			}
			System.out.println("The second second has elapsed; long enough to interrupt myself!");
			Thread.currentThread().interrupt();
		}
	};
	
	public static void main(String[] args) throws Exception {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.printf("Uncaught Throwable %s from Thread %s, on thread %s%n", e, t, Thread.currentThread());
			}
		});
		
		Thread t1 = new Thread(r, "BG Thread");
		t1.setDaemon(false);
		t1.start();
		System.out.printf("Main thread %s, run thread %s%n", Thread.currentThread(), t1);
		try {
			Thread.sleep(1000);
			System.out.println("The first second has elapsed.");
		} catch (InterruptedException e) {
			System.out.println("Interrupt caught in main, on " + Thread.currentThread());
		}
		t1.interrupt();
	}
}
