package PhoneSim;

public class PhoneSystem2 {
	static int nThreads = 0;
	final static int THRESHOLD = 10;
	public static void main(String[] a) throws InterruptedException {
		while (true) {
			while (nThreads > THRESHOLD) {
				try {
					System.out.println("WAITING");
				Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			final PhoneCall c = new PhoneCall();
			Thread t = new Thread(new Runnable() {
				public void run() {
					++nThreads;
					c.call();
					--nThreads;
				}
			});
			t.start();
			Thread.sleep(1000);
		}
	}
}
