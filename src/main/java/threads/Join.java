public class Join {
	public static void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				System.out.println("Reading");
				try {
					System.in.read();
				} catch (java.io.IOException ex) {
					System.err.println(ex);
				}
				System.out.println("Thread Finished.");
			}
		};
		System.out.println("Starting");
		t.start();
		System.out.println("Joining");
		try {
			t.join();
		} catch (InterruptedException ex) {
			// should not happen:
			System.out.println("Who dares interrupt my sleep?");
		}
		System.out.println("Main Finished.");
	}
}
