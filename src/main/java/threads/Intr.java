/**
 * Interrupt a read, and then resume it.
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class Intr extends Thread {
	/** The Thread we are timing. */
	Thread timedThread;

	/** How long to give the Thread */
	int time;

	public Intr(Thread t, int mSec) {
		System.out.println("Intr.<init>: Thread " + t);
		setPriority(MAX_PRIORITY);
		t.setName("TimedThread");
		timedThread = t;
		time = mSec;
	}

	public void run() {
		while (timedThread != null) {
			System.out.println("About to sleep for " + time + "mSec");
			try {
				sleep(time);
			} catch (InterruptedException ie) {
				System.err.println("Unexpectedly interrupted time sleep!");
				return;
			}
			if (timedThread == null)
				return;
			// if we are still here, the timer went off, but the
			// main program hasn't nullified the thread yet.
			System.out.println("Will now interrupt sleep of " + timedThread);
			timedThread.interrupt();
		}
	}

	/** Simple test case */
	public static void main(String[] ap) {
		byte b[] = new byte[10];
		System.out.println("Creating Intr");
		Intr me = new Intr(Thread.currentThread(), 2000);
		me.start();
		System.out.println("Starting read");
		for (int i=1; i<=5; i++) {
			try {
				// This read will block, unless you type something in
				// the console window (and you have to be pretty quick!).
				int n = System.in.read(b);
				System.out.print(">> ");
				for (int j=0; j<n; j++) {
					if (b[j] == '\n' || b[j] == '\r')
						break;
					System.out.print((char)b[j]);
				}
				System.out.println(" <<");
				if (b[0] == (byte)'Q')
					return;

			} catch (java.io.InterruptedIOException e) {
				System.out.println("Interrupted IO");
			} catch (Exception e) {
				// Other exceptions are more painful
				System.out.println("Caught " + e);
			}
		}
		me.timedThread = null;
		System.out.println("All done");
	}
}
