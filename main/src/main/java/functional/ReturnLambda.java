package functional;

public class ReturnLambda {
	public Runnable getRunnable() {
		return () -> { System.out.println("Hello"); };
	}
	
	public static void main(String[] args) {
		System.out.println("ReturnLambda.main()");
	}
}
