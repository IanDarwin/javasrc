package functional;

public class ReturnLambda {
	public Runnable getRunnable() {
		return () -> { System.out.println("Hello"); };
	}
	
	public static void main(String[] args) {
		Runnable runnable = new ReturnLambda().getRunnable();
		System.out.println(runnable);
		runnable.run();
	}
}
