package PhoneSim;

public class PhoneSystem {
	public static void main(String[] a) throws InterruptedException {
		while (true) {
			final PhoneCall c = new PhoneCall();
			Thread t = new Thread(new Runnable() {
				public void run() {
					c.call();
				}
			});
			t.start();
			Thread.sleep(1000);
		}
	}
}
