package functional;

public class ReturnLambda {
	public Runnable getRunnable() {
		return () -> { System.out.println("Hello"); };
	}
}
