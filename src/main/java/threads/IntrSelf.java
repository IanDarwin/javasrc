public class IntrSelf {
	public static void main(String[] args) {
		System.out.println("Starting");
		try {
			Thread.currentThread().interrupt();
		} catch (Throwable x) {
			System.out.println(x);
			return;
		}
		System.out.println("Interrupted Status = " +
			Thread.currentThread().isInterrupted());
		System.out.println("Done");
	}
}
