/**
 * Timer to interrupt a long-running call (like read, write, hence the name).
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class IOTimer extends Thread {
	/** The Thread we are timing. */
	Thread timedThread;
	/** How long to give the Thread */
	int time;
	public IOTimer(Thread t, int mSec) {
		System.out.println("IOTimer.<init>: Thread " + t);
		setPriority(MAX_PRIORITY);
		timedThread = t;
		time = mSec;
	}

	public void run() {
		System.out.println("About to sleep for " + time + "mSec");
		try {
			sleep(time);
		} catch (InterruptedException ie) {
			System.out.println("You interrupted my sleep!");
			return;
		}
		// if we are still here, the timer went off
		System.out.println("Will now interrupt sleep of " + timedThread);
		timedThread.interrupt();
	}
	/** Simple test case */
	public static void main(String ap[]) {
		byte b[] = new byte[10];
		System.out.println("Creating IOTimer");
		new IOTimer(Thread.currentThread(), 1000).start();
		System.out.println("Starting read");
		try {
			System.in.read(b);
		}		catch (Exception e) {
			System.out.println("Caught " + e);
		}
		System.out.println("All done");
	}
}
