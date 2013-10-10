package functional;

public class ProcessIntsUsingFunctional {
	static int[] integers = { 0, 1, 2, 3, 4, 5 };
	
	public static void main(String[] args) {
		int total = 0;
		for (int i : integers) {
			total += -> compute(x) { x * x + 1 };
		}
		System.out.println(total);
	}
}
