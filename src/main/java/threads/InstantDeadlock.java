package threads;

public class InstantDeadlock {
	public static void main(String[] args) throws Exception {
		Thread.currentThread().join();
	}
}
