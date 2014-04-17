/**
 * Fibonacci numbers
 */
public class Fibonacci {
	public static void main(String[] args) {
		System.out.println(fib(5));
	}

	public static int fib(int n) {
		System.out.println( n );
		if (n == 0) 
			return 0;
		if (n == 1)
			return 1;
		return n + fib(n - 1);
	}
}
