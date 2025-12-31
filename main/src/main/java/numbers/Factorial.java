package numbers;

public class Factorial {
	public static long factor(long n) {
		long tot = 0;
		while (n > 0)
			tot += n--;
		return tot;
	}
}
